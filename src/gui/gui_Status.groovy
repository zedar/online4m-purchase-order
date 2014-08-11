package gui

// --- Purchase order status 

class STATUS_COLHandler extends InputHandler {
  void onInit() {
    if (!processStep) {
      self.setVisible(false)
      STATUS.setValue("00")
      PAYMENT_COL.setVisible(false)
    }
    else {
      if (processStep != "REGISTRATION") {
        self.setVisible(true) 
        STATUS.setVisible(true)
        PAYMENT_COL.setVisible(true)
        ADVANCE_PAYMENT.setVisible(true)
        REMAINING_PAYMENT.setVisible(true)
      }
      else {
        PAYMENT_COL.setVisible(false)
        ADVANCE_PAYMENT.setVisible(false)
        REMAINING_PAYMENT.setVisible(false)
      }
    }
  }
}

class STATUSHandler extends InputHandler {
  void onInit() {
    self.setReadOnly(true)  
  }
  
  void onValidate() {
    if (!self.getValue() || self.getValue() == "00") {
      if (processStep && processStep != "REGISTRATION") {
        self.setRequiredValidation("Field is required")
      }
    }
  }
  
  void onChange() {
    def status = self.getValue()
    if (status != "00") {
      PAYMENT_COL.setVisible(true)
      ADVANCE_PAYMENT.setVisible(true)
      REMAINING_PAYMENT.setVisible(true)
    }
    else {
      PAYMENT_COL.setVisible(false)
      ADVANCE_PAYMENT.setVisible(false)
      REMAINING_PAYMENT.setVisible(false)
      ADVANCE_PAYMENT.setValue(null)
      REMAINING_PAYMENT.setValue(null)
    }
  }
}