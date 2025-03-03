import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**Ввести с консоли дату в формата: 31.12.2020, сохранить ее в переменной класса Date (преобразовав введенную строку с использованием SimpleDateFormat)
 *- Увеличить дату на 45 дней и вывести на экран
 *- Сдвинуть дату на начало года и вывести на экран
 *- Увеличить дату на 10 рабочих дней (считаем субботы и воскресенья выходными) и вывести на экран
 *- Ввести с консоли вторую дату в том же формате и сохранить ее в другой переменной класса Date
 *- Посчитать количество рабочих дней (субботы и воскресенья - выходные) между первой и второй датами введенными с консоли и вывести на экран
 **/
public class calendar {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


        System.out.println("Введите дату в формате 'дд.мм.гггг':");
        String dateString1 = scanner.nextLine();
        Date date1 = null;
        try {
            date1 = dateFormat.parse(dateString1);
        } catch (ParseException e) {
            System.err.println("Некорректный формат даты!");
            scanner.close();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);


        calendar.add(Calendar.DAY_OF_YEAR, 45);
        System.out.println("Дата + 45 дней: " + dateFormat.format(calendar.getTime()));


        calendar.set(Calendar.DAY_OF_YEAR, 1);
        System.out.println("Дата на начало года: " + dateFormat.format(calendar.getTime()));

        // 4.
        int workingDaysToAdd = 10;
        int addedDays = 0;
        while (addedDays < workingDaysToAdd) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                addedDays++;
            }
        }
        System.out.println("Дата + 10 рабочих дней: " + dateFormat.format(calendar.getTime()));


        System.out.println("Введите вторую дату в формате 'дд.мм.гггг':");
        String dateString2 = scanner.nextLine();
        Date date2 = null;
        try {
            date2 = dateFormat.parse(dateString2);
        } catch (ParseException e) {
            System.err.println("Некорректный формат даты!");
            scanner.close();
            return;
        }

        // 6.
        int workingDaysBetween = countWorkingDays(date1, date2);
        System.out.println("Количество рабочих дней между датами: " + workingDaysBetween);

        scanner.close();
    }

    //
    public static int countWorkingDays(Date date1, Date date2) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(date1);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(date2);

        // более ранняя
        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            Calendar tempCal = startCal;
            startCal = endCal;
            endCal = tempCal;
        }

        int workingDays = 0;
        while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
            int dayOfWeek = startCal.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
                workingDays++;
            }
            startCal.add(Calendar.DAY_OF_YEAR, 1);
        }

        return workingDays;
    }
}
