package xyz.rodit.dexsearch.cached;

import com.google.common.collect.Lists;
import org.jf.dexlib2.iface.ExceptionHandler;
import org.jf.dexlib2.iface.MethodImplementation;
import org.jf.dexlib2.iface.TryBlock;
import org.jf.dexlib2.iface.debug.DebugItem;
import org.jf.dexlib2.iface.instruction.Instruction;

import java.util.List;

public class CachedMethodImplementation implements MethodImplementation {

    private final MethodImplementation base;

    private int registerCount = Integer.MIN_VALUE;
    private List<Instruction> instructions;

    public CachedMethodImplementation(MethodImplementation base) {
        this.base = base;
    }

    @Override
    public int getRegisterCount() {
        return registerCount == Integer.MIN_VALUE ? registerCount = base.getRegisterCount() : registerCount;
    }

    @Override
    public Iterable<? extends Instruction> getInstructions() {
        return instructions == null ? instructions = Lists.newArrayList(base.getInstructions()) : instructions;
    }

    @Override
    public List<? extends TryBlock<? extends ExceptionHandler>> getTryBlocks() {
        return base.getTryBlocks();
    }

    @Override
    public Iterable<? extends DebugItem> getDebugItems() {
        return base.getDebugItems();
    }
}
