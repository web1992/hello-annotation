package com.web.enums;

/**
 * @desc ����ö��
 * @from Effective Java ����ö��
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
     * ���ݹ��������Ͳ�ͬ���Ѽ��㹤�ʵķ�����һ������ö�� �����й����ļ���
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
