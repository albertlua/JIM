![JIM Header](https://snag.gy/AHIdm1.jpg)

# JIM
Java Inventory Management is software that originated as a final project for an AP Computer Science A class.

https://proj.albertlua.com/jim/

# Developers
- Albert Lua
- Filip Graniczny

# What is JIM?
JIM is a simple utility (_still in the works_), designed to quickly and effectively manage a commercial or personal inventory.
**A few useful features:**
* Connection details are stored in a configuration file (_config.jim_), therefore re-entering details is not necessary.
* Table view of all items in inventory with basic details.
* Real-time data sync.
* Item checkout.
* Edit items within the software.
* Add/remove items within the click of a button.

![Sample data](https://snag.gy/2UKwTY.jpg)

# Requirements
In order to run this software utility as intended, you will need the following:
* A MySQL database.
* Java 8 or newer installed on a Java-supported operating system.
* A working internet connection.

# Download
* **Compiled Executable (JAR)**: https://proj.albertlua.com/jim - _for users who want to simply use the utility and do not want to make any modifications to the source._
* **Full Source (Java)**: https://github.com/albertlua/JIM/archive/master.zip - _for those who want to modify the source and compile the utility themselves._
# Files
You will find three (3) folders in the main directory of the project source:
* _/dependencies_: The JAR files contained in this folder must be added to the library within your IDE project file.
* _/resources_: The fonts and images used within the program. This folder must be defined as the _resource root_ within your Java IDE. Otherwise, you will have to change the directory within the classes.
* _/source_: All of the java files storing the code are in this directory. Be sure to set this directory is the _source root_ in your IDE - if it is not already so by default.
