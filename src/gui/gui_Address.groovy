package gui

import static pl.bpm4cloud.validator.Validator.isValidEmail

// --- Invoice data

class AddressMixin extends InputHandler {
  void onValidate() {
    if (!self.getValue()) {
      self.setRequiredValidation("Field is required") 
    }
  }
}

class NAME1Handler extends AddressMixin {
}

class NIPHandler extends AddressMixin {
}

class MOBILEHandler extends AddressMixin {
}


class EMAILHandler extends AddressMixin {
  void onValidate() {
    super.onValidate()
    if (self.hasErrors()) {
      return 
    }
    def email = self.getValue()
    if (!isValidEmail(email)) {
      self.setRequiredValidation("Incorrect email format")
    }
  }
}

class POSTALHandler extends AddressMixin {
}

class STREETHandler extends AddressMixin {
}

class CITYHandler extends AddressMixin {
}

// --- Shipping address rules

class SADDR_AddressMixin extends InputHandler {
  void onValidate() {
    def isDifferent = SADDR_IS_DIFFERENT.getValue() == "1" ? true : false 
    if (isDifferent) {
      if (!self.getValue()) {
        self.setRequiredValidation("Field is required") 
      }
    }
  }
}

class SHIPPING_ADDRESSHandler extends InputHandler {
  void onInit() {
    def isDifferent = model.getValue("ModelPOFORM.SADDR_IS_DIFFERENT") == "1" ? true : false
    SADDR_POSTAL.setVisible(isDifferent)
    SADDR_STREET.setVisible(isDifferent)
    SADDR_HOUSE.setVisible(isDifferent)
    SADDR_CITY.setVisible(isDifferent)
  }
}

class SADDR_IS_DIFFERENTHandler extends InputHandler {
  void onChange() {
    def isDifferent = self.getValue() == "1" ? true : false 
    SADDR_POSTAL.setVisible(isDifferent)
    SADDR_STREET.setVisible(isDifferent)
    SADDR_HOUSE.setVisible(isDifferent)
    SADDR_CITY.setVisible(isDifferent)
  }
}

class SADDR_POSTALHandler extends SADDR_AddressMixin {
}

class SADDR_STREETHandler extends SADDR_AddressMixin {
}

class SADDR_CITYHandler extends SADDR_AddressMixin {
}

class SADDR_EMAILHandler extends SADDR_AddressMixin {
  void onChange() {
    def email = self.getValue()
    if (email) {
      if (!isValidEmail(self.getValue())) {
        self.setRequiredValidation("Incorrect email format") 
      }
    }
  }
}
