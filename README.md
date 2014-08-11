# online4m.com - source code for purchase order process.

[online4m](https://www.online4m.com) is the generation PaaS BPM - Platform as a Service BPM, the most pragmatic was to developing business forms and processes in the cloud.

## Installation procedure

### Step 1: download repository

  $ git clone https://github.com/zedar/online4m-purchase-order.git

### Step 2: login into online4m

Login to your account in *online4m* service - [*online4m/Sign In*](http://online4m/login/auth).  
If you do not have account register new one - [*online4m/Sign Up*](http://online4m.com/usr/create).

### Step 3: create new project

Name it for example PURCHASE ORDER with Code Name PURCHASE_ORDER.

### Step 4: import form definition

Open form definition editor by selecting *New/Form Design* in toolbar under *Navigation* panel. Then select *Serialize Form Design to JSON*.  

In the dialog *GUI serialized to JSON* replace json string with the content of file:

    ./form/POFORM.json

Save form by selecting *Save Form Design* from the toolbar under *Navigation* panel.

In the dialog *Save Form Design* select project name (as was created in *Step 3*) and enter following attributes:

* *Code Name* - **POFORM**
* *Name* - **POFORM** 


### Step 5: import process definition

Open process definition designer by selecting *New/Process Design* in toolbar under *Navigation* panel. Then select *Serializa Process Design to JSON*.  

In the dialog *Process serialized to JSON* replace json string with the content of file:

    ./process/POPROCESS.json

Save process by selecting *Save Process Design* from the toolbar under *Navigation* panel. 

In the dialog *Save Process Design* select project name (as was created in *Step 3*) and enter following attributes:

* *Code Name* - **POPROCESS**
* *Name* - **POPROCESS**

### Step 6: import web forms control rules

Open code editor by selecting *New/Control Rule File* in toolbar under *Navigation* panel.  
Copy and paste source code into the editor from the file:

    ./src/gui/gui_Main.groovy

Save ruleset by selecting *Save Control Ruleset* from the toolbar under *Navigation* panel. 

In the dialog *Save Control Ruleset* select project name (as was created in *Step 3*) and enter following attributes:

* *Code Name* - **gui_Main**

Repeat above steps with following files:

    ./src/gui/gui_Address.groovy
    ./src/gui/gui_Buttons.groovy
    ./src/gui/gui_Dictionaries.groovy
    ./src/gui/gui_Shipping.groovy
    ./src/gui/gui_Status.groovy

### Step 7: import process control rules


### Step 8: run the process

From top menu select *Toolbox/Process Manager*. Find *Project: PURCHASE ORDER[PURCHASE_ORDER]* and then link to start process: *Process: POPROCESS, POPROCESS*. Click it. Main form should be visible.

Click the button *Add product*. Select example product, enter quantity, add another product and quantity.

Enter *Invoice data* and optionally *Shipping address*.

Select *Payment method*.

Anytime you can save process data by clicking the button *Save*. You can cancel order by clicking *Cancel order* button. You can submit order by clicking the button *Process order*.

Running processes are visible on task list. From the top menu select *Toolbox/Form & Process Action*. Select *PURCHASE_ORDER* task list. Find your process instance on task list. Anytime you can double click on record from task list in order to continue further processing.
In **test/test.html** folder there is example page that displays first online form.
