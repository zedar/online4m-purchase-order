package gui

// --- Shipping method & price

class SHIPPING_METHODHandler extends InputHandler {
  void onInit() {
    def sm = model.getValue("ModelPOFORM.SHIPPING_METHOD")
    if (!sm) {
      self.setValue("00")
    }
    else {
      self.setValue(sm) 
    }
  }
  
  void onValidate() {
    def val = self.getValue()
    if (!val || val == "00") {
      self.setRequiredValidation("Shipping method is required") 
    }
  }
  
  void onChange() {
    def val = self.getValue()
    
    // set default price
    switch(val) {
      case "CU":
        SHIPPING_PRICE.setReadOnly(false)
        SHIPPING_PRICE.setObjectValue(SHIPPING_PRICING.CU.value())
        break
      case "CT":
        SHIPPING_PRICE.setReadOnly(false)
        SHIPPING_PRICE.setObjectValue(SHIPPING_PRICING.CT.value())
        break
      case "PC":
        SHIPPING_PRICE.setReadOnly(false)
        SHIPPING_PRICE.setObjectValue(SHIPPING_PRICING.PC.value())
        break
      default:
        SHIPPING_PRICE.setReadOnly(true)
        SHIPPING_PRICE.setObjectValue(null)
        break
    }
    
    BigDecimal grossPrice = GROSS_PRICE.getObjectValue()
    BigDecimal deliveryPrice = SHIPPING_PRICE.getObjectValue()
    if (grossPrice != null && deliveryPrice != null) {
      TOTAL_PRICE.setObjectValue(grossPrice + deliveryPrice)
    }
    else {
      TOTAL_PRICE.setObjectValue(null) 
    }
  }
}

class SHIPPING_PRICEHandler extends InputHandler {
  void onInit() {
    def sm = model.getValue("ModelPOFORM.SHIPPING_METHOD")
    if (!sm || sm == "00") {
      self.setReadOnly(true)
      self.setObjectValue(null)
    }
    else {
      self.setReadOnly(false) 
    }
  }
  
  void onChange() {
    BigDecimal deliveryPrice = self.getObjectValue()
    BigDecimal grossPrice = GROSS_PRICE.getObjectValue()
    if (deliveryPrice && grossPrice) {
      TOTAL_PRICE.setObjectValue(deliveryPrice + grossPrice) 
    }
  }
}