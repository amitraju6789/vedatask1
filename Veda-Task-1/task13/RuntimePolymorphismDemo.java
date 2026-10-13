package task13;

// Base Parent Class
class NotificationService {
    public void sendNotification(String message) {
        System.out.println("[Default Notification] Sending message: " + message);
    }
}

// Child Class 1: Email Notification
class EmailNotification extends NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("[Email Service] Sending Email alert: " + message);
    }
}

// Child Class 2: SMS Notification
class SMSNotification extends NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("[SMS Service] Sending SMS text: " + message);
    }
}

// Child Class 3: Push Notification
class PushNotification extends NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("[Push Notification] Sending Mobile Push alert: " + message);
    }
}

public class RuntimePolymorphismDemo {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("    RUNTIME POLYMORPHISM DEMONSTRATION   ");
        System.out.println("=========================================");

        // Using parent reference variables to hold different child objects (Dynamic Method Dispatch)
        NotificationService service1 = new EmailNotification();
        NotificationService service2 = new SMSNotification();
        NotificationService service3 = new PushNotification();

        // Executing overridden methods at runtime
        System.out.println("\n--- Triggering Notifications ---");
        service1.sendNotification("Your server deployment was successful!");
        service2.sendNotification("Your OTP verification code is 4829.");
        service3.sendNotification("New connection request received.");

        // Demonstrating polymorphic behavior in a loop / collection array
        System.out.println("\n--- Polymorphic Array Execution ---");
        NotificationService[] services = {
            new EmailNotification(),
            new SMSNotification(),
            new PushNotification()
        };

        for (NotificationService ns : services) {
            // JVM determines which overridden method to call at runtime based on the actual object type
            ns.sendNotification("System maintenance scheduled for midnight.");
        }

        System.out.println("\n=========================================");
        System.out.println("Polymorphism execution completed successfully!");
    }
}