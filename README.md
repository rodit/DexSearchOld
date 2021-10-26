# DexSearch
Schematic based pattern search to find obfuscated classes and fields across APK versions.

## Schematics
DexSearch works by looking for patterns in classes in dex files loaded from an APK. Schematics must be defined by the user and
require some initial research (i.e. using a tool like [jadx](https://github.com/skylot/jadx) to reverse engineer an app). From
these schematics, a set of bindings is generated as well as a set of Java classes which can be used in an Xposed module. For 
an example of this, see [SnapMod](https://github.com/rodit/SnapMod).

Schematics are meant to resemble parts of Java classes. For example, if we know an APK contains an obfuscated class compiled 
from the following Java source code:

```java
public class FlagsManager {

  // some flags...
  private static final String FLAG_ADS_DISABLED = "no_ads";
  // more flags...
  private static final String FLAG_PREMIUM = "oremium";
  
  private final Map<String, String> _flags = new HashMap<String, String>();
  
  // many other flag boolean functions...
  
  public boolean areAdsDisabled() {
    return _flags.get(FLAG_ADS_DISABLED) == "yes";
  }
  
  public boolean isPremium() {
    return _flags.get(FLAG_PREMIUM) == "yes";
  }
}
```

When compiled, obfuscated and decompiled again (e.g. by jadx), this class might look like this:

```java
public class C0329XAj {
  
  private static final String fAeGjjA = "no_ads";
  private static final String fAJEje9 = "premium";
  
  private final Map A = new HashMap();
  
  public boolean K() {
    // final String references typically replaced by constant string value.
    return A.get("no_ads") == "yes";
  }
  
  public boolean La() {
    return A.get("premium") == "yes";
  }
}
```

Although a very simple example, this is somewhat similar to how Spotify perform premium and ad checks.

To create a schematic to find this class with DexSearch, we can create a new text file with the following contents:

```
class FlagsManager {
  java.util.Map flagMap;
  
  boolean areAdsDisabled() {
    .string "no_ads";
  }
  
  boolean isPremium() {
    .string "premium";
  }
}
```

This schematic should be enough to differentiate the `FlagsManager` class from every other class in the APK.

### Class Modifiers
Modifiers can be added to classes (preceeding the `class` keyword) to change how their search works.
| Modifier | Purpose |
| --- | --- |
| certain | The class is searched for first. This is typically used in enums which can help identify other classes. |
| exact | The class should only be matched if it contains the exact methods and fields declared in the schematic. |
| late | The class is late-bound. This means the class is only bound when it is referenced by another class in the schematic. |
| fuzzy | The class is fuzzy matched. Fuzzy matches fail on the first mismatch as usual but still return the best match if no perfect match is found. |
| very fuzzy | Very fuzzy does not short circuit matching. The closest class in the APK is returned as a match for this class. Very fuzzy classes are useful for research (i.e. if small changes have been made across builds) but cannot reference late bound types. |

Illogical combinations of these modifiers will cause DexSearch to fail (combining `late` and `certain`, for example, makes no sense).

Additionally, classes can be required to be an `enum` or `interface` by replacing `class` with either of those two terms.

### Class References
Class names (types) can be marked as a reference. References to other classes in the schematic must be prefixed with a `!`.
For example, if we wanted to specify a second class in our `FlagsManager` schematic from above that contains a field of type 
`FlagsManager`, we would do the following:

```
class FlagsManager {
  ... same as before
}

class Globals {
  !FlagsManager flagsManager;
}
```

Note that to reference another class like this, the referenced class must have been already resolved (either decalred above the 
referencer or marked with the `certain` modifier). Here, `Globals` will be matched to any class with a field of whatever type 
`FlagsManager` was resolved (matched) as. Such references can be made anywhere a class name is used (fields, methods, bytecode 
matchers, etc).

Class names can also be marked as latent. Latent class names are matched in-place (when they are first seen). For example, we
could write the following schematic:

```
class Globals {
  #FlagsManager flagsManager;
}
```

Here, when `Globals` is matched (in this case it will be matched to the first declared class in the APK which has one field as it 
contains no other constraints), `FlagsManager` is resolved as the type of the first field declared in the matched class. Additional
constraints can be added to `FlagsManager` (and subsequently `Globals`) by adding a late-bound definition for `FlagsManager` to the
schematic:

```
late class FlagsManager {
  java.util.Map mapField;
  java.lang.String stringField;
}
```

Now, `Globals` will only be matched to a class if it contains a field which has a type that matches `FlagsManager`.

### Fields
Adding fields to a class schematic is similar to adding fields to a class in Java as seen in the above examples. What hasn't 
been shown is the following:

```java
// Matches the first string field with any name.
java.lang.String stringField;

// Matches the first field of any type with any name.
* firstField;

// Matches the first field of any type called 'exactNameField'.
* $exactNameField;

// Matches the first string field but does not produce a binding for it (this is useful to consume fields you don't care about).
java.lang.String _;

// Matches the first 3 string fields and does not produce bindings for them.
java.lang.String?3 _;

// Matches the first static field of any type.
static * firstStaticField;

// Matches the non-extistence of a string field.
~java.lang.String _;
```

Important notes:
- `$` before a field name ensures the dex name of the field exactly matches the field name in the schematic.
- Giving a field `_` as a name discards the field and does not produce a binding for it.
- `*` can be used as a wildcard for type matching (will match any type).
- Fields are matched in order of declaration in the class being tested. Once a field is matched, it is consumed.
- If no `static` modifier is present, static fields *can* still be matched.
- `type?N _` can be used to discard the first `N` fields of type `type`.
- `type? optionalField` can be used to specify that the field is not required for a successful match, but if found it should
still be bound to `optionalField`.
- `~` before a field (or method) ensures a class with a matching member is *not* bound to the containing schematic.

### Methods
Similarly, methods can be added to class schematics as follows:

```java
// Matches the first void returning method with no arguments and binds it to 'testMethod'.
void testMethod()

// Matches the first method with any return type which takes arguments of the specified types.
* methodWithArguments(java.lang.String, java.util.Map, int)

// Matches the first method with matching arguments and then any other arguments afterwards.
* methodWithAnyArguments(java.lang.String, java.util.Map, ...)

// Matches an instance constructor with the specified arguments.
void $<init>(android.content.Context, java.net.Uri)
```

Important notes:
- `...` can be used to match any arguments following a (optionally 0-length) sequence of type-matched arguments.
- `$` before a method name ensures the dex name of the method exactly matches the method name in the schematic.
- `_` works as a discard for methods in the same way it does for fields.
- `*` can be used as a type wildcard in return and argument types of methods.
- Methods are matched in order of declaration and consumed when matched (as with fields).
- Methods support the `~` not matching and `static` modifier as with fields.

### Method Bodies
Some elements of method bodies can be matched by inspecting the dex bytecode of a method. This is particularly 
useful when methods contain strings which can be used to identify them. Here are examples of all the supported 
method bytecode matchers:

```java
void exampleMethod() {
  // String Matching:
  // Matches the given string constant.
  .string "Exact String";
  // Matches a constant string containing the given string.
  .string contains "Exact";
  // Matches a given string constant, ignoring case.
  .string caseless "exact string";
  // Note, contains and caseless can be combined:
  .string contains caseless "exact";
  
  // Method Call Matching
  // Matches a call to Double.isNaN().
  .call java.lang.Double->isNaN;
  // Matches a call to a method called 'namedMethod' on any type.
  .call *->namedMethod;
  // Matches a call to a method called 'namedMethod' and binds the calling type to 'LateType'.
  .call #LateType->namedMethod;
  
  // Class Instantiation
  // Matches a call to new Object().
  .new-instance java.lang.Object;
  // Matches the instantiation of a previously resolved type bound to 'FlagsManager'.
  .new-instance !FlagsManager;
  
  // Type References
  // Matches any kind of reference to the type Object.
  .reference java.lang.Object;
  // Matches any kind of reference to the previously resolved 'FlagsManager'.
  .reference !FlagsManager;
  
  // Field References
  // Matches a reference to any field called 'namedField'.
  .field *->namedField;
  // Matches a reference to the 'map' field of the previously resolved 'FlagsManager'.
  .field !FlagsManager->map;
  
  // Bytecode Constants
  // Matches an integer constant with value 321.
  .const 321;
  // Matches an integer constant with value 1 (equivalent to true in dex bytecode).
  .const 1;
}
```

### Inheritance
Inheritance can be modelled in a schematic using the same syntax as Java:

```java
class ExampleClass extends java.lang.Object implements java.util.List {
  ...schema fields and methods
}
```

Using `extends` checks all parent types until `java.lang.Object` is found.

Type matching with derived types can be performed as follows:

```java
// Declare schematic for the base type (or we can use a constant type name without '!' later).
class ChatModelBase {
  
  // toString methods often have useful identification information such as the original class name.
  java.lang.String $toString() {
    .string contains "ChatModelBase";
  }
}

class ChatModelViewHolder {
  
  // Matches the first field whose type extends the resolved 'ChatModelBase' type.
  [extends !ChatModelBase] chatModel;
}
```
