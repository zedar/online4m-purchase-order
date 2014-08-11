package gui

// --- Purchase order controls

class POFORMHandler extends InputHandler {
  void onInit() {
    NET_PRICE.setReadOnly(true)
    GROSS_PRICE.setReadOnly(true)
    TOTAL_PRICE.setReadOnly(true)
    
    if (processId) {
      PROCESS_ID.setText("P.O. number: " + processId?.toString())
    }
  }
}

class NET_PRICEHandler extends InputHandler {
  void onInit() {
    self.setReadOnly(true) 
  }
}

class GROSS_PRICEHandler extends InputHandler {
  void onInit() {
    self.setReadOnly(true) 
  }
}

class TOTAL_PRICEHandler extends InputHandler {
  void onInit() {
    self.setReadOnly(true) 
  }
}

// --- Purchase order lines

class ADD_OLHandler extends OnClickHandler {
  // Add new order line
  void onAction() {
    model.addListRecord("ModelPOFORM.ROW_OL")
  }
}

class DEL_OLHandler extends OnClickHandler {
  // Delete order line
  void onAction() {
    def idx = self.listIndex()
    if (idx >= 0) {
      model.removeListRecord("ModelPOFORM.ROW_OL", idx)
      BigDecimal netSum = ROW_OL.sum("OL_NET_PRICE")
      BigDecimal grossSum = ROW_OL.sum("OL_GROSS_PRICE")
      if (netSum != null) {
        NET_PRICE.setObjectValue(netSum) 
      }
      if (grossSum != null) {
        GROSS_PRICE.setObjectValue(grossSum)
      }
    }
  }
}

class OL_PRODUCTHandler extends InputHandler {
  // Set pricing based on product selection
  void onValidate() {
    if (!self.getValue()) {
      self.setRequiredValidation("Product is required") 
    }
  }
  
  void onChange() {
    def product = self.getValue()
    def price = null
    switch(product) {
      case "P01":
        price = PRICING.P01.value()
        break
      case "P02":
        price = PRICING.P02.value()
        break
      case "P03":
        price = PRICING.P03.value()
        break
      default:
        return
    }   
    if (price) {
      OL_AMOUNT.setObjectValue(1)
      OL_PRICE.setObjectValue(price)
      OL_NET_PRICE.setObjectValue(price)
      OL_GROSS_PRICE.setObjectValue(price + price*0.23g)
      BigDecimal netSum = ROW_OL.sum("OL_NET_PRICE")
      BigDecimal grossSum = ROW_OL.sum("OL_GROSS_PRICE")
      if (netSum != null) {
        NET_PRICE.setObjectValue(netSum) 
      }
      if (grossSum != null) {
        GROSS_PRICE.setObjectValue(grossSum)
      }
    }
  }
}

class OL_AMOUNTHandler extends InputHandler {
  void onValidate() {
    if (!self.getValue()) {
      self.setRequiredValidation("Quantity of products is required") 
    }
  }
  
  void onChange() {
    def amount = self.getObjectValue()
    BigDecimal price = OL_PRICE.getObjectValue()
    if (!amount || !price) {
      OL_NET_PRICE.setObjectValue(0g)
      OL_GROSS_PRICE.setObjectValue(0g)
      return
    }
    
    price = price * amount
    OL_NET_PRICE.setObjectValue(price)
    OL_GROSS_PRICE.setObjectValue(price + price*0.23g)
    BigDecimal netSum = ROW_OL.sum("OL_NET_PRICE")
    BigDecimal grossSum = ROW_OL.sum("OL_GROSS_PRICE")
    if (netSum != null) {
      NET_PRICE.setObjectValue(netSum) 
    }
    if (grossSum != null) {
      GROSS_PRICE.setObjectValue(grossSum) 
    }
  }
}

class OL_PRICEHandler extends InputHandler {
  void onInit() {
    self.setReadOnly(true) 
  }
}

class OL_NET_PRICEHandler extends InputHandler {
  void onInit() {
    self.setReadOnly(true) 
  }
}

class OL_GROSS_PRICEHandler extends InputHandler {
  void onInit() {
    self.setReadOnly(true) 
  }
}