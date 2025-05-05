# SauceDemo Login Test Plan

## Introduction

This test plan outlines the approach for validating the login functionality of the SauceDemo website (https://www.saucedemo.com/). The plan includes test cases covering positive paths, negative paths, and edge cases to ensure the login system functions correctly and securely.

## Test Environment
- **Website URL**: https://www.saucedemo.com/
- **Browsers**: Firefox
- **Devices**: Desktop

## Known Valid Credentials (from documentation)
- Standard User: `standard_user` / `secret_sauce`
- Locked Out User: `locked_out_user` / `secret_sauce`
- Problem User: `problem_user` / `secret_sauce`
- Performance Glitch User: `performance_glitch_user` / `secret_sauce`
- Error User: `error_user` / `secret_sauce`
- Visual User: `visual_user` / `secret_sauce`

## Test Cases

### 1. Positive Test Cases

| ID | Description | Steps | Test Input | Expected Results | Actual Results | Status |
|----|-------------|-------|------------|------------------|----------------|--------|
| TC-P01 | Login with Standard User Credentials | 1. Navigate to https://www.saucedemo.com/<br>2. Enter username<br>3. Enter password<br>4. Click Login button | Username: `standard_user`<br>Password: `secret_sauce` | User successfully logs in and is redirected to the inventory page | | |
| TC-P02 | Login with Performance Glitch User | 1. Navigate to https://www.saucedemo.com/<br>2. Enter username<br>3. Enter password<br>4. Click Login button | Username: `performance_glitch_user`<br>Password: `secret_sauce` | User logs in successfully after a noticeable delay and is redirected to the inventory page | | |
| TC-P03 | Login with Error User | 1. Navigate to https://www.saucedemo.com/<br>2. Enter username<br>3. Enter password<br>4. Click Login button | Username: `error_user`<br>Password: `secret_sauce` | User logs in successfully and is redirected to the inventory page (may display certain errors during site usage) | | |
| TC-P04 | Login with Visual User | 1. Navigate to https://www.saucedemo.com/<br>2. Enter username<br>3. Enter password<br>4. Click Login button | Username: `visual_user`<br>Password: `secret_sauce` | User logs in successfully and is redirected to the inventory page (with visual inconsistencies) | | |
| TC-P05 | Login with Remember Me Functionality | 1. Navigate to https://www.saucedemo.com/<br>2. Enter username<br>3. Enter password<br>4. Check "Remember me" checkbox (if available)<br>5. Click Login button<br>6. Log out<br>7. Revisit the login page | Username: `standard_user`<br>Password: `secret_sauce`<br>Remember me: checked | Username field should be pre-populated with the previously used username | | |

### 2. Negative Test Cases

| ID | Description | Steps | Test Input | Expected Results | Actual Results | Status |
|----|-------------|-------|------------|------------------|----------------|--------|
| TC-N01 | Login with Locked Out User | 1. Navigate to https://www.saucedemo.com/<br>2. Enter username<br>3. Enter password<br>4. Click Login button | Username: `locked_out_user`<br>Password: `secret_sauce` | Login fails with appropriate error message indicating the user is locked out | | |
| TC-N02 | Login with Invalid Username | 1. Navigate to https://www.saucedemo.com/<br>2. Enter username<br>3. Enter password<br>4. Click Login button | Username: `invalid_user`<br>Password: `secret_sauce` | Login fails with appropriate error message indicating invalid credentials | | |
| TC-N03 | Login with Invalid Password | 1. Navigate to https://www.saucedemo.com/<br>2. Enter username<br>3. Enter password<br>4. Click Login button | Username: `standard_user`<br>Password: `wrong_password` | Login fails with appropriate error message indicating invalid credentials | | |
| TC-N04 | Login with Empty Username | 1. Navigate to https://www.saucedemo.com/<br>2. Leave username field empty<br>3. Enter password<br>4. Click Login button | Username: ` ` (empty)<br>Password: `secret_sauce` | System displays error message requiring username field to be filled | | |
| TC-N05 | Login with Empty Password | 1. Navigate to https://www.saucedemo.com/<br>2. Enter username<br>3. Leave password field empty<br>4. Click Login button | Username: `standard_user`<br>Password: ` ` (empty) | System displays error message requiring password field to be filled | | |
| TC-N06 | Login with Both Fields Empty | 1. Navigate to https://www.saucedemo.com/<br>2. Leave username field empty<br>3. Leave password field empty<br>4. Click Login button | Username: ` ` (empty)<br>Password: ` ` (empty) | System displays error message requiring username field to be filled (first validation error) | | |

### 3. Edge Cases and Security Tests

| ID | Description | Steps | Test Input | Expected Results | Actual Results | Status |
|----|-------------|-------|------------|------------------|----------------|--------|
| TC-E01 | Login with Extremely Long Username | 1. Navigate to https://www.saucedemo.com/<br>2. Enter extremely long username<br>3. Enter password<br>4. Click Login button | Username: [100 character string]<br>Password: `secret_sauce` | System either truncates input appropriately or displays an error message about input length | | |
| TC-E02 | Login with Extremely Long Password | 1. Navigate to https://www.saucedemo.com/<br>2. Enter username<br>3. Enter extremely long password<br>4. Click Login button | Username: `standard_user`<br>Password: [100 character string] | System either truncates input appropriately or displays an error message about input length | | |
| TC-E03 | Login with Special Characters in Username | 1. Navigate to https://www.saucedemo.com/<br>2. Enter username with special characters<br>3. Enter password<br>4. Click Login button | Username: `user!@#$%^&*()`<br>Password: `secret_sauce` | System handles special characters appropriately (either rejects with clear error or sanitizes input) | | |
| TC-E04 | Login with Whitespace Only in Fields | 1. Navigate to https://www.saucedemo.com/<br>2. Enter spaces in username field<br>3. Enter spaces in password field<br>4. Click Login button | Username: `   ` (multiple spaces)<br>Password: `   ` (multiple spaces) | System recognizes inputs as empty or invalid and shows appropriate validation message | | |
| TC-E05 | Login Field Case Sensitivity | 1. Navigate to https://www.saucedemo.com/<br>2. Enter uppercase username<br>3. Enter uppercase password<br>4. Click Login button | Username: `STANDARD_USER` (all uppercase)<br>Password: `SECRET_SAUCE` (all uppercase) | If case-sensitive, login should fail with appropriate error message | | |
| TC-E06 | Browser Back Button After Logout | 1. Successfully login as `standard_user`<br>2. Navigate to several pages within the application<br>3. Logout<br>4. Click browser back button multiple times | Username: `standard_user`<br>Password: `secret_sauce`<br>Action: Browser back button after logout | User should not be able to access protected content after logout | | |

### Test Summary
- Total test cases executed
- Pass/fail statistics
- Critical issues identified
- Recommendation for release readiness