# Status Report: Webshop Beta Testing

### Project: Webshop in Beta
### Date: 2024.09.19.
### Confidentiality Level: 91 %

## Overview
The testing of the [Swag Labs](https://www.saucedemo.com/) webshop (beta version) has been completed successfully, with a total of 16 test cases executed. Out of these, 15 passed and 1 failed, resulting in an overall success rate of 93.75%. The tests covered critical functionalities, including product handling, user login, and checkout processes.

## Key Results

### Total Tests Executed: 16
- **Passed:** 15
- **Failed:** 1

### Detailed Breakdown:

1. **Product Handling Tests (Total: 7)**
    - **Passed:** 7
    - **Tests included:**
        - Adding a single item to the cart from the product details page
        - Adding multiple items to the cart from both the shop and product details pages
        - Testing cart behavior after adding and removing items
        - Checking all products’ individual pages with detailed information

2. **Login Page Tests (Total: 6)**
    - **Passed:** 6
    - **Tests included:**
        - Successful logins with various user types, including standard, locked-out, problem, and error users
        - Performance-related tests with specific user conditions

3. **Checkout Tests (Total: 3)**
    - **Passed:** 2
    - **Failed:** 1
    - **Failed Test: `checkoutWithEmptyCartTest`**
        - **Reason:** The test expected the checkout process to fail when the cart was empty, but the checkout was still completed successfully.
        - **Assertion failed:** "Checkout Complete - process should not be successful, because the cart is empty!"

## Conclusion
The testing process was largely successful, with a 93.75% pass rate. However, the failed test in the checkout process with an empty cart highlights a critical bug that needs to be addressed. Further investigation is recommended to fix this issue before moving forward with the production release.

### Attachment: Test results
![Test results](./image/testresults.png)
