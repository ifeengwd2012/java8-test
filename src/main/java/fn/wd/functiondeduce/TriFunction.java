package fn.wd.functiondeduce;

/**
 * Created by fengweidong on 2016/12/24.
 */
@FunctionalInterface
public interface TriFunction<T,R,K,Y> {

    Y get(T t,R r,K k);

}
