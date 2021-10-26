package xyz.rodit.dexsearch.schema2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchMetrics {

    public static final float TYPE = 5f;
    public static final float INHERITANCE = 4f;
    public static final float ANNOTATIONS = 2f;
    public static final float FIELD = 2f;
    public static final float METHOD = 2f;

    private final ClassMatch owner;
    private final List<FailureReason> reasons = new ArrayList<>();
    private float score;

    public MatchMetrics(ClassMatch owner) {
        this.owner = owner;
    }

    public ClassMatch getOwner() {
        return owner;
    }

    public float getScore() {
        return score;
    }

    public void matched(float score) {
        this.score += score;
    }

    public List<FailureReason> getReasons() {
        return reasons;
    }

    public void fail(String rule, String message, FailureType type) {
        reasons.add(new FailureReason(rule, message, type));
    }

    @Override
    public String toString() {
        return "MatchMetrics{" +
                "owner=" + owner +
                ", reasons=" + Arrays.toString(reasons.toArray()) +
                ", score=" + score +
                '}';
    }

    public enum FailureType {
        CLASS_TYPE,
        INHERITANCE,
        ANNOTATIONS,
        FIELD,
        METHOD
    }

    public class FailureReason {

        private final String rule;
        private final String message;
        private final FailureType type;

        public FailureReason(String rule, String message, FailureType type) {
            this.rule = rule;
            this.message = message;
            this.type = type;
        }

        @Override
        public String toString() {
            return "FailureReason{" +
                    "rule='" + rule + '\'' +
                    ", message='" + message + '\'' +
                    ", type=" + type +
                    '}';
        }
    }
}
