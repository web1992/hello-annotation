package com.web.enums;

/**
 * @desc 策略枚举
 * @from Effective Java 策略枚举
 */
public enum PayrollDay {

    MONDAY(PayType.WEEKDAY),
    TUESDAY(PayType.WEEKDAY),
    WEDNESDAY(PayType.WEEKDAY),
    THURSDAY(PayType.WEEKDAY),
    FRIDAY(PayType.WEEKDAY),
    SATURDAY(PayType.WEEKEND),
    SUNDAY(PayType.WEEKEND);

    private final PayType payType;

    PayrollDay(PayType payType) {
        this.payType = payType;
    }

    double pay(double hoursWorked, double payRate) {
        return payType.pay(hoursWorked, payRate);
    }

    //the strategy enum type

    /**
     * 根据工作日类型不同，把计算工资的方法给一个策略枚举 来进行工作的计算
     */
    private enum PayType {
        WEEKDAY {
            double overtimePay(double hours, double payRate) {
                return hours <= HOURS_PER_SHIFT ? 0 : (hours - HOURS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            double overtimePay(double hours, double payRate) {
                return hours * payRate / 2;
            }
        };

        private static final int HOURS_PER_SHIFT = 8;

        abstract double overtimePay(double hoursWorked, double payRate);

        double pay(double hoursWorked, double payRate) {
            double basePay = hoursWorked * payRate;
            return basePay + overtimePay(hoursWorked, payRate);
        }
    }
}

class PayrollDayTest {

    public static void main(String[] args) {
        System.out.println(PayrollDay.MONDAY.pay(12d, 12d));
        System.out.println(PayrollDay.TUESDAY.pay(12d, 12d));
        System.out.println(PayrollDay.WEDNESDAY.pay(12d, 12d));
        System.out.println(PayrollDay.THURSDAY.pay(12d, 12d));
        System.out.println(PayrollDay.FRIDAY.pay(12d, 12d));
        System.out.println(PayrollDay.SATURDAY.pay(12d, 12d));
        System.out.println(PayrollDay.SUNDAY.pay(12d, 12d));

    }

}
