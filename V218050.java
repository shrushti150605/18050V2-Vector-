import java.util.Scanner;
import java.util.Vector;
import java.util.Date;

class Patient {
    int patientID;
    String name;
    int age;
    String disease;

    public Patient(int patientID, String name, int age, String disease) {
        this.patientID = patientID;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    public void displayPatientDetails() {
        System.out.println("Patient ID: " + patientID);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Disease: " + disease);
    }
}

class Appointment {
    int appointmentID;
    int patientID;
    Date appointmentDate;
    String doctorAssigned;

    public Appointment(int appointmentID, int patientID, Date appointmentDate, String doctorAssigned) {
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.appointmentDate = appointmentDate;
        this.doctorAssigned = doctorAssigned;
    }

    public void displayAppointmentDetails() {
        System.out.println("Appointment ID: " + appointmentID);
        System.out.println("Patient ID: " + patientID);
        System.out.println("Appointment Date: " + appointmentDate);
        System.out.println("Doctor Assigned: " + doctorAssigned);
    }
}

class Ward {
    int wardNumber;
    int capacity;
    Vector<Patient> assignedPatients;

    public Ward(int wardNumber, int capacity) {
        this.wardNumber = wardNumber;
        this.capacity = capacity;
        this.assignedPatients = new Vector<>();
    }

    public boolean assignPatient(Patient patient) {
        if (assignedPatients.size() < capacity) {
            assignedPatients.add(patient);
            return true;
        } else {
            return false;
        }
    }

    public void displayWardDetails() {
        System.out.println("Ward Number: " + wardNumber);
        System.out.println("Capacity: " + capacity);
        System.out.println("Assigned Patients: ");
        for (Patient patient : assignedPatients) {
            patient.displayPatientDetails();
        }
    }
}

class Bill {
    int patientID;
    double totalCost;
    String servicesProvided;

    public Bill(int patientID, double totalCost, String servicesProvided) {
        this.patientID = patientID;
        this.totalCost = totalCost;
        this.servicesProvided = servicesProvided;
    }

    public void displayBillDetails() {
        System.out.println("Patient ID: " + patientID);
        System.out.println("Total Cost: " + totalCost);
        System.out.println("Services Provided: " + servicesProvided);
    }
}

public class V218050 {
    static Vector<Patient> patients = new Vector<>();
    static Vector<Appointment> appointments = new Vector<>();
    static Vector<Ward> wards = new Vector<>();
    static Vector<Bill> bills = new Vector<>();

    public static void addPatient() {
        Scanner scanner = new Scanner(System.in);

        int patientID = 0;
        boolean validID = false;
        while (!validID) {
            System.out.print("Enter Patient ID: ");
            if (scanner.hasNextInt()) {
                patientID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                validID = true;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for Patient ID.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        while (name.matches(".\\d.")) {
            System.out.println("Invalid input. Patient name should not contain numbers. Please enter again:");
            name = scanner.nextLine();
        }

        int age = 0;
        boolean validAge = false;
        while (!validAge) {
            System.out.print("Enter Patient Age: ");
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                if (age > 0 && age <= 120) {
                    validAge = true;
                } else {
                    System.out.println("Invalid input. Age must be between 1 and 120. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer for age.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        System.out.print("Enter Disease: ");
        String disease = scanner.nextLine();
        while (disease.matches(".\\d.")) {
            System.out.println("Disease name should not contain numbers. Please enter again:");
            disease = scanner.nextLine();
        }

        patients.add(new Patient(patientID, name, age, disease));
        System.out.println("Patient added successfully!");
    }

    public static void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients available.");
        } else {
            for (int i = 0; i < patients.size(); i++) {
                System.out.println("\n--- Patient " + (i + 1) + " ---");
                patients.get(i).displayPatientDetails();
            }
        }
    }

    public static void searchPatientByID(int patientID) {
        boolean found = false;
        for (Patient patient : patients) {
            if (patient.patientID == patientID) {
                System.out.println("\nPatient found:");
                patient.displayPatientDetails();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No patient found with ID " + patientID);
        }
    }

    public static void addAppointment() {
        Scanner scanner = new Scanner(System.in);

        int appointmentID = 0;
        boolean validID = false;
        while (!validID) {
            System.out.print("Enter Appointment ID: ");
            if (scanner.hasNextInt()) {
                appointmentID = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                validID = true;
            } else {
                System.out.println("Invalid input. Please enter a valid integer for Appointment ID.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        System.out.print("Enter Patient ID: ");
        int patientID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Date appointmentDate = new Date();

        System.out.print("Enter Doctor Assigned: ");
        String doctorAssigned = scanner.nextLine();

        appointments.add(new Appointment(appointmentID, patientID, appointmentDate, doctorAssigned));
        System.out.println("Appointment added successfully!");
    }

    public static void assignWard() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Ward Number: ");
        int wardNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Ward ward = null;
        for (Ward w : wards) {
            if (w.wardNumber == wardNumber) {
                ward = w;
                break;
            }
        }
        if (ward == null) {
            System.out.print("Enter Capacity for Ward: ");
            int capacity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            ward = new Ward(wardNumber, capacity);
            wards.add(ward);
        }

        System.out.print("Enter Patient ID to assign to the ward: ");
        int patientID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Patient patient = null;
        for (Patient p : patients) {
            if (p.patientID == patientID) {
                patient = p;
                break;
            }
        }

        if (patient != null && ward.assignPatient(patient)) {
            System.out.println("Patient assigned to ward " + wardNumber + " successfully.");
        } else {
            System.out.println("Failed to assign patient to ward.");
        }
    }

    public static void generateBill() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Patient ID: ");
        int patientID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        double totalCost = 0;
        boolean validCost = false;
        while (!validCost) {
            System.out.print("Enter Total Bill Cost: ");
            if (scanner.hasNextDouble()) {
                totalCost = scanner.nextDouble();
                if (totalCost > 0) {
                    validCost = true;
                } else {
                    System.out.println("Total cost should be positive. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for the cost.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Services Provided: ");
        String servicesProvided = scanner.nextLine();

        bills.add(new Bill(patientID, totalCost, servicesProvided));
        System.out.println("Bill generated successfully!");
    }

    public static void displayAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
        } else {
            for (Appointment appointment : appointments) {
                appointment.displayAppointmentDetails();
            }
        }
    }

    public static void displayWards() {
        if (wards.isEmpty()) {
            System.out.println("No wards available.");
        } else {
            for (Ward ward : wards) {
                ward.displayWardDetails();
            }
        }
    }

    public static void displayBills() {
        if (bills.isEmpty()) {
            System.out.println("No bills available.");
        } else {
            for (Bill bill : bills) {
                bill.displayBillDetails();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n--- Hospital Management System ---");
            System.out.println("1. Add Patient");
            System.out.println("2. Display All Patients");
            System.out.println("3. Search Patient by ID");
            System.out.println("4. Add Appointment");
            System.out.println("5. Assign Ward");
            System.out.println("6. Generate Bill");
            System.out.println("7. Display Appointments");
            System.out.println("8. Display Wards");
            System.out.println("9. Display Bills");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    displayAllPatients();
                    break;
                case 3:
                    System.out.print("Enter Patient ID to search: ");
                    int searchID = scanner.nextInt();
                    searchPatientByID(searchID);
                    break;
                case 4:
                    addAppointment();
                    break;
                case 5:
                    assignWard();
                    break;
                case 6:
                    generateBill();
                    break;
                case 7:
                    displayAppointments();
                    break;
                case 8:
                    displayWards();
                    break;
                case 9:
                    displayBills();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}