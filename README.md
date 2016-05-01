## play-order

A simple net payment calculator that can apply a series of rule based discounts to a service.  Entry point is the AmountPayableServiceImpl.
  The design is an immuatable model(Shopping Cart) that can be applied to a service (AmountPayableService).  Expectations are a web application
  that would be deployed in spring or similar.

  1. If the user is an employee of the store, he gets a 30% discount.
  2. If the user is an affiliate of the store, he gets a 10% discount.
  3. If the user has been a customer for over 2 years, he gets a 5% discount.
  4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
  5. The percentage based discounts do not apply on groceries.
  6. A user can get only one of the percentage based discounts on a bill.

# API usage
Commons lang3 for hashcode, equals.
Javax.money for money representation (later multi-currency support).

# Design Patterns
Strategy for various discount rules.
Command for execution of rules.
Immutable model style for easier port to remote api calls.
Interface from Implementation on an expectation that this would be a bean injection into a larger system.
 
# Build Notes
 Use maven 3.2+ with Java 8+
 mvn clean package to run.
 build includes tests, coverage (site/jacoco/index.html), checkstyle support.
