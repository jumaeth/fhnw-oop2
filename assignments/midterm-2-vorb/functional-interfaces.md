# Einige Funktionsinterfaces in der Java-Standardbibliothek

## Package `java.lang`

| Interface       | Description                                                                                                          |
|-----------------|----------------------------------------------------------------------------------------------------------------------|
| `AutoCloseable` | An object that may hold resources (such as file or socket handles) until it is closed.                               |
| `Comparable<T>` | This interface imposes a total ordering on the objects of each class that implements it.                             |
| `Readable`      | A `Readable` is a source of characters.                                                                              |
| `Runnable`      | The `Runnable` interface should be implemented by any class whose instances are intended to be executed by a thread. |


## Package `java.util`

| Interface       | Description                                                                                                                 |
|-----------------|-----------------------------------------------------------------------------------------------------------------------------|
| `Comparator<T>` | A comparison function, which imposes a total ordering on some collection of objects.                                        |
| `Formattable`   | Must be implemented by any class that needs to perform custom formatting using the 's' conversion specifier of `Formatter`. |


## Package `java.util.function`

| Interface           | Description                                                                                                      |
|---------------------|------------------------------------------------------------------------------------------------------------------|
| `BiConsumer<T,U>`   | Represents an operation that accepts two input arguments and returns no result.                                  |
| `BiFunction<T,U,R>` | Represents a function that accepts two arguments and produces a result.                                          |
| `BinaryOperator<T>` | Represents an operation upon two operands of the same type, producing a result of the same type as the operands. |
| `BiPredicate<T,U>`  | Represents a predicate (`boolean`-valued function) of two arguments.                                             |
| `Consumer<T>`       | Represents an operation that accepts a single input argument and returns no result.                              |
| `Function<T,R>`     | Represents a function that accepts one argument and produces a result.                                           |
| `Predicate<T>`      | Represents a predicate (`boolean`-valued function) of one argument.                                              |
| `Supplier<T>`       | Represents a supplier of results.                                                                                |
| `UnaryOperator<T>`  | Represents an operation on a single operand that produces a result of the same type as its operand.              |