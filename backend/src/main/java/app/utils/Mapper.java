package app.utils;

import java.util.Collection;

public interface Mapper<S,T> {

    public T convertToDTO(S source);

    public S convertToEntity(T target);

    public Collection<T> covertAllToDTO(Collection<S> sources);

    public Collection<S> covertAllToEntity(Collection<T> entities);

}
