package operations;

public interface Operation<T> {
    T add(T x, T y);
    T sub(T a, T b);
    T mul(T a, T b);
    T neg(T a);
    boolean more(T a, T b);
    boolean less(T a, T b);
    boolean eq(T a, T b);
    T parseValue(String a);
}
