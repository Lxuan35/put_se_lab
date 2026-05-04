Louan HAMON -
University ID : ER-2198

# Answers to the questions : 

### 1. Have your tests found any errors in the Calculator code?

Yes, this test for the `multiply(x, y)` method failed : 
```
assertEquals(24, c.multiply(6, 4));
```

It was expecting $24$, when the actual result is $2$. So let's check the method's implementation. Indeed, there was a mistake in the method : it was returning $x - y$ instead of $x * y$. I fixed the bug, then the test passed without errors.

### 2. What is the result for the test you just added?

I wrote this assertion for `addPositiveNumbers(x, y)` : 
```
c.addPositiveNumbers(-3, 5);
```

After running it, this error occurs : 

`java.lang.IllegalArgumentException: The arguments must be positive`

So I added this call to the $assertThrows$ method : 
```    
assertThrows(IllegalArgumentException.class, () -> {
    c.addPositiveNumbers(-3, 5);
    }, "Should throw IllegalArgumentException when x or y is negative"       
```
Then I ran again the test. This time, the 3 tests passed.

### 3.1. Would the tests stop working if the setUp annotation was changed from BeforeEach to BeforeAll? Justify your answer.

Yes, it would stop working, for two main reason.
First, the methods annotated with `@BeforeAll` must be declared as static because they are executed before the test class itself is even instantiated. So by just changing the annotation to `@BeforeAll`, Junit will skip the tests.

Then, if we changed the `setUp()` method to be static, the tests would start failing due to state contamination.
- `@BeforeEach` guarantees a clean environment for every single test. If `testAdd` modifies an object, it doesn't matter, because `testMultiply` will get a brand new object.
- `@BeforeAll` runs once for the entire test class. All the test methods would end up sharing the exact same setup. This breaks a fundamental rule of unit testing: tests must be completely independent of one another.

### 4.1. Which method will be marked as Failure and which will be marked as Error?

After running the tests, I can see the differences between both : 

- `test1` is considered as a failure : the code didn't crash, but the expected outcome was incorrect.
- `test2` is marked as an error : the test crashes unexpectedly before it can even finish running.

### 4.2. What type of thrown object does JUnit expect to determine that the test has failed in terms of the Failure category (it will be marked with an exclamation mark against a yellow circle).

JUnit expects an object of type `AssertionError` to be thrown, to categorize a test result as a failure.

```
org.opentest4j.AssertionFailedError: This will fail ==> expected: <true> but was: <false>
```

### 5.1. What type of testing is it: black-box or white-box?

It's white-box testing, because to determine the execution paths through the calculate method, we have to look at its control flow statements and design the test cases to trigger those specific branches.

### 5.2. How many possible paths are there in calculate, assuming the start point is the start of the method and the end point is the end?

There are 4 possible paths in `calculate` : 

- Path 1: The customer is a subscriber.
    - Condition: `customer.isSubscriber()` is true.
    - Returns $0.0$.

- Path 2: The customer is not a subscriber, but has a SILVER loyalty level.
    - Conditions: `customer.isSubscriber()` is false, AND `getLoyaltyLevel() == SILVER` is true.
    - Returns $0.9 * startingPrice$.

- Path 3: The customer is not a subscriber, and has a GOLD loyalty level.
    - Conditions: `customer.isSubscriber()` is false, `getLoyaltyLevel() == SILVER` is false, AND `getLoyaltyLevel() == GOLD` is true.
    - Returns $0.8 * startingPrice$.

- Path 4: The customer is not a subscriber, and has a STANDARD loyalty level.
    - Conditions: `customer.isSubscriber()` is false, `getLoyaltyLevel() == SILVER` is false, AND `getLoyaltyLevel() == GOLD` is false.
    - Returns the standard startingPrice

In this way, we have to write 4 distinct test cases to trigger each of these specific return statements.