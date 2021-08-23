![logo](images/logo_name.png)
# StuBankPLC

StuBankPLC is a banking app created to be used by students, it has a simple GUI designed to be used
 by a student, who may not have used a bank account before. The program is intuitive and vibrant, 
 aiming to take away from other similar apps which are more business focused.

## Installation StuBank PLC

is an android app, developed and ran in Android Studio, the app can be run on either the android 
studio emulator or on your own android device.

### Running on Android Emulator

To run the app on the emulator select app from the dropdown in the run section on android studio.
 You can then select a device for the program to be emulated on, we chose "Pixel_3a_API_30_x86" and that works fine, however this app has been designed on any android device using any version of android so other options should also work.                

![logo](https://raw.githubusercontent.com/JakubRoks1/ReadmeTest/main/1.png?token=AHO2K7HE6CKNPJJDKNQK6LDADF35I)

### Running on Android Device

To run on an android device, first connect the device up via USB cable. Then open settings, and tap
 about phone 7 times, you can then return to the previous screen and enable USB debugging. Now to 
 run the program select your phone from available devices (where you would've selected a device to
  emulate on) and click run, the app should open up once the build is complete.

![logo](https://raw.githubusercontent.com/JakubRoks1/ReadmeTest/main/2.png?token=AHO2K7ARUIBF6OOVWMQZJE3ADF4DE)

## Using StuBankPLC

### Account creation
Once on the app you have the option of either logging in or creating an account. Either option will then
prompt you to enter a code sent to your email, once you have entered this you will be logged into the app and can create 
your first accounts. Note that because this isn't a real banking app using real money you can create an account and 
deposit however much you want. Once you have created an account you will be able to deposit funds into an account, as 
well as transfer between accounts, you will also be able to make transactions which will take funds out of your 
accounts.

### Pay
Pay allows you to transfer funds between accounts, this can be either between 2 of your own accounts, or between 1 of 
your accounts and someone else's account, just enter the amount you want to transfer, and the account you want to 
transfer to, if the credentials are correct and your account contains sufficient funds the transaction will go through
and you will have evidence of the transaction on your account transactions.
### Purchase
Purchase allows you to make a purchase of a product (although the physical transaction doesn't occur), just select the 
price of the product and what it's type is, if you have sufficient funds the transaction will go through, funds will be 
deducted and you will have a new purchase on the transactions page.
### Withdraw
Withdraw allows you to extract funds from the account, select the amount you want to withdraw, and if you have 
sufficient funds the money will be removed from your account and this will be reflected in your transactions page, this 
feature would be used when a StuBank user wishes to draw funds from a cash machine etc.

### Login
After creating an account, every time you wish to access your account you should use the login page, fill in your 
account number and password and if correct you will be redirected to the 2FA page, during this time you will have been 
emailed a confirmation code which you can use to gain access to your account.

### Admin
Creating and logging into your admin account is exactly the same as if you were a user, just select the admin option 
from the main page, once here you have access to user details (minus passwords) as well as the functionality to delete
accounts if required.

### Additional Notes
We understand if we were to release this app on the play store it shouldn't have an admin section, 
if we were to implement this app fully we would have all the admin functionality in a separate app
that the user does not have access to, and there would also be nowhere to create an admin account. 
However for the purposes of this project we have included functionality in the app itself.
      
      
 

