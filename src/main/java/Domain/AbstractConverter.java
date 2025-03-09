package Domain;

import java.text.ParseException;

public abstract class AbstractConverter<T>{
    public abstract String toString(T item);
    public abstract T fromString(String string) throws ParseException;
}
