package gui

// Common dictionaries

// pricing per product 
enum PRICING {
  P01(3.80),
  P02(75.0),
  P03(102.0)
  
  PRICING(BigDecimal value) { this.value = value }
  private final BigDecimal value
  BigDecimal value() { return this.value }
}

// default pricing for delivery method
enum SHIPPING_PRICING {
  CU(100.0),
  PC(0.0),
  CT(50.0)
  
  SHIPPING_PRICING(BigDecimal value) { this.value = value }
  private final BigDecimal value
  BigDecimal value() { return this.value }
}