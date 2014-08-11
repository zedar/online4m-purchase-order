package gui

class BTN_SAVEHandler extends OnClickHandler {
  void onInit() {
    if (!processStep || processStep in ["SDCancelState", "SDEndState"]) {
      self.setVisible(false) 
    } 
  }
  
  void onAction() {
    if (!processStep) {
      return 
    }
    gui.saveSection("POFORM")
    gui.showMessage('<strong>Form data saved.<strong>', 'success')
  }
}

class BTN_CANCELHandler extends OnClickHandler {
  void onInit() {
    if (!processStep || processStep in ["INVOICING", "SDCancelState", "SDEndState"]) {
      self.setVisible(false) 
    }
  }
  
  void onAction() {
    if (!processStep) {
      return 
    }
    process.executeTask("toSDCancelState")
  }
}

class BTN_PROCESSHandler extends OnClickHandler {
  void onInit() {
    if (!processStep || processStep in ["SDCancelState", "SDEndState"]) {
      self.setVisible(false) 
    }
  }
  
  void onAction() {
    println "PROCESS onAction: ${processStep}"
    boolean showError = false
    if (ROW_OL.size() == 0) {
      gui.showMessage('<strong>Purchase order has to have at least one product.</strong>', 'error')
      showError = true
    }
    if (!gui.validateSection("POFORM")) {
      showError = true
      def validationMap = gui.getValidationResult("POFORM")
      println "VALIDATION MAP: ${validationMap}"
    }
    if (!showError) {
      String nextStep = null
      println("PROCESS STEP: ${processStep}")
      switch(processStep) {
        case "REGISTRATION":
          STATUS.setValue("01")
          nextStep = "toPRODUCTION"
          break
        case "PRODUCTION":
          STATUS.setValue("02")
          nextStep = "toSHIPPING"
          break
        case "SHIPPING":
          STATUS.setValue("03")
          nextStep = "toSDEndState"
          break
        default:
          return
      }
      
      gui.saveSection("POFORM")
      
      
      if (processStep == "REGISTRATION") {
        String email = model.getValue("ModelPOFORM.EMAIL")
        if (email) {
          mail.sendMail([email] as String[], "Purchase order confirmation", """\
          <html>
          <body>
            <h1>Thank You</h1>
            <p>Your purchase order number is: <strong>${processId}</strong></p>
          </body>
          </html>
          """)  
        } 
      }
      if (nextStep) {
        process.executeTask(nextStep)  
      }
    }
    else {
      println "SHOW ERRORS"
    }
  }
}