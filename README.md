# Cultro Roulette Project Overview

## Overview

The **Cultro Roulette Project** is a comprehensive framework consisting of multiple modules, each designed to address specific functionalities such as caching, geometry, string manipulation, reflection, and more. The primary modules covered in this project so far include the **Caching Module**, the **Geometry Module**, the **Language Module**, the **Reflection Module**, the **Security Module**, and the **Utils Module**. These modules offer various features, from efficient data caching mechanisms to flexible geometric computations, enhanced string utilities, advanced reflection capabilities, comprehensive security functionalities, and a suite of utility functions. This makes the project suitable for a wide range of applications, including scientific computing, simulations, general software optimization, and data security.

## Modules Overview

### Caching Module

The **Caching Module** is designed to provide in-memory caching functionality with various eviction policies to manage the cache effectively.

#### Key Features

- **AbstractCache**: An abstract class providing a framework for creating cache implementations. It includes methods for inserting, removing, and clearing cache entries, with thread-safe operations.
- **Eviction Policies**: The module supports multiple cache eviction policies, ensuring flexibility in managing cached data:
  - **FIFO (First-In-First-Out)**: Evicts the oldest cached entry.
  - **LRU (Least Recently Used)**: Evicts the entry that has not been accessed for the longest time.
  - **LFU (Least Frequently Used)**: Evicts the entry with the lowest access frequency.
- **Cache Implementations**: Concrete implementations for each eviction policy:
  - **`FIFOCache`**: Implements a simple FIFO eviction mechanism.
  - **`LRUCache`**: Uses a combination of a hash map and a doubly linked list to keep track of recently used entries.
  - **`LFUCache`**: Maintains frequency counts for each cache entry, providing an efficient mechanism for least frequently used eviction.

#### Example Usage

To create and use a cache with LRU eviction:

```java
import org.cultro.roulette.cache.Cache;
import org.cultro.roulette.cache.CacheFactory;

public class CacheUsageExample {
    public static void main(String[] args) {
        CacheFactory.EvictionPolicy policy = CacheFactory.EvictionPolicy.LRU;
        Cache<String, String> cache = CacheFactory.createCache(policy, 3);
        cache.put("A", "Apple");
        cache.put("B", "Banana");
        cache.put("C", "Cherry");
        cache.get("A");
        cache.put("D", "Date"); // "B" will be evicted as it is least recently used
        System.out.println(cache.containsKey("B")); // Outputs: false
    }
}
```

### Geometry Module

The **Geometry Module** provides a comprehensive framework for working with geometric shapes and vectors in multi-dimensional space. The module is designed to facilitate complex geometric calculations, such as calculating volumes, surface areas, intersections, and more for a variety of shapes, including n-dimensional orthotopes, 3D spheres, cubes, polygons, and other geometric primitives. The module provides reusable classes to aid in both 2D and 3D geometric computations, offering an extensible toolkit for a wide range of applications.

#### Key Features

- **N-Dimensional Geometric Calculations**: Provides support for vectors, locations, and shapes in both 2D and 3D, as well as n-dimensional space.
- **Flexible Vector Operations**: Classes like `Vector2D`, `Vector3D`, and `NDVector` support vector addition, subtraction, scaling, magnitude, normalization, and dot products.
- **Geometric Shapes and Primitives**: Classes to represent and manipulate shapes like spheres, cubes, rectangles, and polygons.
- **Iterator Support for Shapes**: Many shapes, including `Sphere` and `Cube`, offer iterators to traverse all grid points within or on the boundary of these objects.
- **Validation and Error Handling**: Built-in validation to prevent misuse, such as creating zero-volume orthotopes or providing null parameters.

#### Supported Classes

##### Vector Classes

- **`Vector2D`** and **`Vector3D`**: Represent 2D and 3D vectors, respectively, with common operations like cross products (for 3D), dot products, and normalization.
- **`NDVector`**: Represents vectors in arbitrary dimensions, providing basic vector arithmetic operations.

##### Location Classes

- **`Location2D`** and **`Location3D`**: Represent points or locations in 2D and 3D space, respectively.
- **`NDLocation`**: Represents locations in arbitrary-dimensional space, supporting basic operations like addition and subtraction.

##### Geometric Shapes

- **`Orthotope`**: Represents an n-dimensional hyperrectangle. Supports volume, surface area calculations, and provides an iterator to traverse points inside.
- **`Cube`**: Represents a cube in 3D space defined by two opposite corners. Provides methods for volume and surface area calculation, as well as point iteration.
- **`Sphere`**: Represents a sphere in 3D space, defined by a center and radius. Supports volume, surface area calculations, and provides an iterator over the points within.
- **`Rectangle`**: Represents a 2D rectangle defined by two opposite corners. Provides methods for area, perimeter, and point iteration.
- **`Polygon`**: Represents a general polygon in 2D space, defined by an ordered list of vertices. Supports area and perimeter calculations and includes an iterator for the points inside.
- **`Circle`**: Represents a 2D circle defined by a center and radius. Supports calculations for area, circumference, and provides an iterator over its boundary points.
- **`AABB`**** (Axis-Aligned Bounding Box)**: Represents a 3D bounding box useful for collision detection and spatial representation in games or simulations.

#### Example Usage

To create and manipulate a `Vector3D`:

```java
import org.cultro.roulette.geometry.d3.Vector3D;

public class GeometryUsageExample {
    public static void main(String[] args) {
        Vector3D vectorA = new Vector3D(1, 2, 3);
        Vector3D vectorB = new Vector3D(4, 5, 6);
        Vector3D crossProduct = vectorA.cross(vectorB);
        System.out.println("Cross Product: " + crossProduct);
    }
}
```

To create an `Orthotope` and calculate its volume:

```java
import org.cultro.roulette.geometry.dn.Orthotope;
import org.cultro.roulette.geometry.dn.NDLocation;

public class OrthotopeExample {
    public static void main(String[] args) {
        NDLocation corner1 = new NDLocation(new double[]{0, 0, 0});
        NDLocation corner2 = new NDLocation(new double[]{3, 4, 5});
        Orthotope orthotope = new Orthotope(corner1, corner2);
        System.out.println("Volume: " + orthotope.getVolume()); // Outputs: 60.0
    }
}
```

### Language Module

The **Language Module** provides utilities for string manipulation and validation to facilitate operations commonly needed in text processing and data validation tasks.

#### Key Features

- **`RString`**: A rich wrapper around Java's `String` class, providing additional methods for advanced string operations.
  - **String Operations**: Methods for case-insensitive replacement, substring extraction, joining sequences, hex conversion, and more.
  - **Character and Byte Manipulation**: Supports converting strings to bytes with specific encodings and character manipulation like reversing.
  - **Formatting**: Provides static methods for formatted string creation with `format()` similar to `String.format()`.

#### Example Usage

To use `RString` for advanced string operations:

```java
import org.cultro.roulette.lang.RString;

public class RStringExample {
    public static void main(String[] args) {
        RString rstr = new RString("Hello World");
        RString replaced = rstr.replaceIgnoreCase("world", "Java");
        System.out.println(replaced); // Outputs: Hello Java
    }
}
```

- **`Validate`**: A utility class providing various methods for validation, such as checking for null values, validating numeric properties (e.g., positive, negative, even, odd), and validating array or collection indexes.

#### Example Usage

To use the `Validate` class for validation:

```java
import org.cultro.roulette.lang.Validate;

public class ValidateExample {
    public static void main(String[] args) {
        try {
            Validate.notNull(null, "Object must not be null");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Outputs: Object must not be null
        }
    }
}
```

### Reflection Module

The **Reflection Module** facilitates introspection, dynamic invocation, and metadata caching for Java classes, fields, and methods. This module provides enhanced reflective capabilities, making it easier to dynamically load classes, invoke methods, and cache metadata efficiently. It integrates well with the **Caching Module** to improve performance by reducing repetitive reflection calls.

#### Key Features

- **Metadata Caching**: Efficiently caches metadata for classes, methods, and fields to avoid redundant reflection operations.
  - **ReflectionCache** and **ReflectionCacheManager** handle caching of metadata with customizable cache sizes and eviction policies.
- **Dynamic Class Loading**: Uses the **DynamicClassLoader** to load classes at runtime, enabling flexible and runtime-driven behavior.
- **Reflection-Based Invokers**:
  - **ConstructorInvoker**: Dynamically create instances using constructors.
  - **FieldAccessor**: Get or set field values reflectively.
  - **MethodInvoker**: Invoke methods on objects using reflection.
- **Proxy Creation**: Utilities to create dynamic proxies for interfaces, enabling additional behavior through delegation.
  - **ProxyFactory** and **ReflectionProxy**: Create proxy instances that delegate to target objects while allowing interception.
- **Annotation and Class Scanners**:
  - **AnnotationScanner, ClassScanner, FieldScanner, MethodScanner**: Retrieve metadata for annotations, classes, fields, and methods, enabling advanced analysis of Java classes.

#### Example Usage

To dynamically create an instance of a class using a constructor:

```java
import org.cultro.roulette.reflection.invokers.ConstructorInvoker;
import org.cultro.roulette.lang.ReflectionException;

public class ConstructorInvokerExample {
    public static void main(String[] args) {
        try {
            MyClass instance = ConstructorInvoker.createInstance(MyClass.class, "parameter");
            System.out.println("Instance created: " + instance);
        } catch (ReflectionException e) {
            e.printStackTrace();
        }
    }
}
```

To get and set a field value using reflection:

```java
import org.cultro.roulette.reflection.invokers.FieldAccessor;
import org.cultro.roulette.lang.ReflectionException;

public class FieldAccessorExample {
    public static void main(String[] args) {
        MyClass instance = new MyClass();
        try {
            FieldAccessor.setFieldValue(instance, "fieldName", "newValue");
            Object value = FieldAccessor.getFieldValue(instance, "fieldName");
            System.out.println("Field value: " + value);
        } catch (ReflectionException e) {
            e.printStackTrace();
        }
    }
}
```

To create a proxy for an interface:

```java
import org.cultro.roulette.reflection.proxies.ProxyFactory;
import org.cultro.roulette.lang.ReflectionException;

public class ProxyFactoryExample {
    public static void main(String[] args) {
        try {
            MyInterface proxy = ProxyFactory.createProxy(MyInterface.class, new MyInterfaceImpl());
            proxy.someMethod();
        } catch (ReflectionException e) {
            e.printStackTrace();
        }
    }
}
```

### Security Module

The **Security Module** offers robust encryption and hashing functionalities to enhance data security and integrity. This module provides various symmetric and asymmetric encryption algorithms as well as multiple hashing mechanisms suitable for different security needs.

#### Key Features

- **Encryption Algorithms**: Supports both symmetric and asymmetric encryption.
  - **Symmetric Ciphers**: AES, DES, Triple DES, Blowfish implementations with multiple modes of operation (e.g., CBC, ECB).
  - **Asymmetric Ciphers**: RSA, DSA encryption and digital signature functionalities.
- **Hashing Algorithms**: Comprehensive support for cryptographic hashing.
  - **Standard Hash Functions**: MD5, SHA-1, SHA-224, SHA-256, SHA-384, SHA-512.
  - **Advanced Hash Functions**: Argon2, RIPEMD (128, 160, 256, 320), Keccak (128, 224, 256, 288, 384, 512), SHA-3 family (224, 256, 384, 512), CRC (16, 32), SHAKE (SHAKE128, SHAKE256).

#### Supported Classes

##### Symmetric Encryption

- **`AESCipher`**: Provides AES encryption with various modes such as CBC and ECB.
- **`DESCipher`** and **`TripleDESCipher`**: Implement the DES and Triple DES encryption algorithms.
- **`BlowfishCipher`**: Uses Blowfish encryption with CBC and PKCS5 padding.

##### Asymmetric Encryption

- **`RSACipher`**: Implements RSA encryption for secure data transmission.
- **`DSACipher`**: Provides digital signature capabilities using DSA.

##### Hashing Algorithms

- `Argon2Hasher`: Advanced memory-hard password hashing with configurable parameters.
- `SHA1Hasher`, `SHA224Hasher`, `SHA256Hasher`, `SHA384Hasher`, `SHA512Hasher`: Standard secure hashing with SHA algorithms.
- `SHA3_224Hasher`, `SHA3_256Hasher`, `SHA3_384Hasher`, `SHA3_512Hasher`: Implements the SHA-3 family of hash functions.
- `Keccack128Hasher`, `Keccack224Hasher`, `Keccack256Hasher`, `Keccack288Hasher`, `Keccack384Hasher`, `Keccack512Hasher`: Implements the Keccak family of hash functions.
- `RIPEMD128Hasher`, `RIPEMD160Hasher`, `RIPEMD256Hasher`, `RIPEMD320Hasher`: Implements RIPEMD hashing functions for data integrity.
- `CRC16Hasher`, `CRC32Hasher`: Implements CRC checksums for data integrity.
- `ShakeHasher`: Implements SHAKE (SHAKE128, SHAKE256) for variable-length output hashing.

#### Example Usage

To encrypt and decrypt using AES:

```java
import org.cultro.roulette.security.encryption.symmetric.aes.AESCipher;
import org.cultro.roulette.security.encryption.symmetric.aes.AESEncryptionMode;

public class AESExample {
    public static void main(String[] args) {
        AESCipher aesCipher = new AESCipher(256, AESEncryptionMode.AES_CBC_PKCS5PADDING);
        byte[] key = aesCipher.generateKey();
        byte[] plaintext = "Sensitive data".getBytes();

        // Encrypt the plaintext
        byte[] encryptedData = aesCipher.encrypt(plaintext, key);
        System.out.println("Encrypted Data: " + new String(encryptedData));

        // Decrypt the ciphertext
        byte[] decryptedData = aesCipher.decrypt(encryptedData, key);
        System.out.println("Decrypted Data: " + new String(decryptedData));
    }
}
```

To create a hash using SHA-256:

```java
import org.cultro.roulette.security.hashing.sha.SHA256Hasher;

public class HashingExample {
    public static void main(String[] args) {
        SHA256Hasher hasher = new SHA256Hasher();
        byte[] data = "Data to hash".getBytes();
        byte[] hash = hasher.digest(data);
        System.out.println("Hash: " + new String(hash));
    }
}
```

### Utils Module

The **Utils Module** provides a set of utility classes designed to assist in various common programming tasks, including Boolean operations, byte array manipulation, character checks, class loading, cooldown mechanisms, enumeration utilities, geometry calculations, I/O operations, map utilities, mathematical calculations, number comparisons, object utilities, reflection utilities, string operations, and type compatibility checks.

#### Key Features

- **`ArrayUtils`**: Provides array-related operations including merging arrays, inserting elements, removing elements, cloning, reversing, and checking if an array is sorted.
- **`BooleanUtils`**: Provides operations such as AND, OR, XOR for boolean values, negation, and comparison.
- **`ByteUtils`**: Utilities for byte array operations such as conversion between hexadecimal strings and byte arrays, and concatenation of byte arrays.
- **`CharacterUtils`**: Provides character-related operations like checking ASCII properties, comparisons, and converting characters to their integer values.
- **`ClassLoaderUtils`**: Utility for dynamically loading classes using a custom class loader.
- **`Cooldown`**: Manages cooldown timers for generic objects, tracking time since last use and allowing cooldown checks.
- **`EnumUtils`**: Utility methods to handle enumerations, including finding enums by name, ignoring case, and validating if a value is part of an enumeration.
- **`GeometryUtils`**: Provides geometric calculations like finding intersections, and calculating distances between points or vectors.
- **`IOUtils`**: Provides utilities for performing various input/output operations such as copying directories, files, creating backups, and checking file properties.
- **`MapUtils`**: A set of tools to work with maps, including filtering, sorting, inverting, deep copying, and more.
- **`MathUtils`**: Provides mathematical utilities like min, max, factorial calculations, prime checking, power-of-two checks, and integration using the trapezoidal rule.
- **`NumberUtils`**: Simple number comparison utilities for different number types.
- **`ObjectUtils`**: Provides deep copy utilities, type checks, and utility methods to manipulate sets and lists of objects.
- **`ReflectionUtils`**: Offers common reflection operations, such as finding methods or fields in classes, getting metadata, and dynamically loading classes from packages.
- **`StringUtils`**: Provides string-related utilities like calculating edit distance, joining sequences, and checking if a string can be parsed as a number.
- **`TypeUtils`**: Utility for checking type compatibility, converting primitives to their wrapper types, and other type-related operations.

#### Example Usage

To merge two arrays using `ArrayUtils`:

```java
import org.cultro.roulette.util.ArrayUtils;

public class ArrayUtilsExample {
    public static void main(String[] args) {
        Integer[] array1 = {1, 2, 3};
        Integer[] array2 = {4, 5, 6};
        Integer[] mergedArray = ArrayUtils.addAll(array1, array2);
        for (Integer i : mergedArray) {
            System.out.print(i + " "); // Outputs: 1 2 3 4 5 6
        }
    }
}
```
