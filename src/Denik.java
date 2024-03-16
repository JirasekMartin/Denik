import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class Denik {

    private LinkedList<Zaznam> zaznamy = new LinkedList<>();
    private Zaznam aktualniZaznam;
    private Scanner sc = new Scanner(System.in);

    public void Program() {

        String prikaz = "";

        while (!prikaz.equals("zavri")) {
            VytiskniMenu();

            if (aktualniZaznam != null) {
                try {
                    aktualniZaznam = zaznamy.getLast();
                    VytiskniPosledniZaznam();
                } catch (Exception e) {
                    // System.out.println("Deník je prázdný.");
                }
            }

            System.out.print("Zadejte příkaz: ");
            prikaz = sc.nextLine();

            switch (prikaz) {
                case "zaznamy":
                    UkazVsechnyZaznamy();
                    break;

                case "novy":
                    VytvorNovyZaznam();
                    break;

                case "smaz":
                    if (zaznamy.size() != 0) {
                        SmazZaznam();
                    } else {
                        System.out.println("\n Deník je prázdný prosím vytvořte nový záznam \n");
                    }
                    break;

                case "zavri":
                    System.out.println("Zavírám deník ");
                    break;
            }
        }

    }

    void VytiskniPocetZaznamu() {
        System.out.println("------------------------------------------");
        System.out.println("Počet záznamů: " + zaznamy.size());
        System.out.println("------------------------------------------");
    }

    void VytiskniMenu() {
        System.out.println("----------------   MENU   ----------------");
        System.out.println("Deník se ovládá následujícími příkazy:");
        System.out.println("- novy: Vytvoření nového záznamu");
        System.out.println("- zaznamy: Zobrazí všechny záznamy");
        System.out.println("- smaz: Odstranění záznamu");
        System.out.println("- zavri: Zavření deníku a ukončení programu");
        System.out.println("----------------   MENU   ----------------");
        VytiskniPocetZaznamu();
    }

    void VytvorNovyZaznam() {
        System.out.println("------------------------------------------");
        System.out.println("Zadejte text k záznamu: ");

        LocalDate datumVytvoreniZaznamu = LocalDate.now();
        String text = sc.nextLine();

        Zaznam zaznam = new Zaznam(datumVytvoreniZaznamu, text);
        zaznamy.add(zaznam);

        aktualniZaznam = zaznam;

        System.out.println("Záznam byl vytvořen");
        System.out.println("------------------------------------------");
    }

    void UkazVsechnyZaznamy() {
        VytiskniPocetZaznamu();

        System.out.println("------------------------------------------");
        for (int i = 0; i < zaznamy.size(); i++) {
            System.out.println("Záznam [" + i + "] vytvořen " + zaznamy.get(i).getDatum() + ":");
            System.out.println(zaznamy.get(i).getText() + "\n");

        }
        System.out.println("------------------------------------------");
    }

    void VytiskniPosledniZaznam() {
        System.out.println("------------------------------------------");
        System.out.println("Poslední záznam:");
        System.out.println(aktualniZaznam.getText());
        System.out.println("Vytvořen: " + aktualniZaznam.getDatum());
        System.out.println("------------------------------------------");
    }

    void SmazZaznam() {

        UkazVsechnyZaznamy();

        boolean jeSpravne = false;
        /* Kontolní boolean, který slouží k potvrzení zadání správného vstupu,
         * v případě nesprávného zadání se vypíše varovná zpráva a cyklus while pokračuje,
         * dokud uživatel nezadá platný index
         */
        while (!jeSpravne) {
            try {
                System.out.println("Jaký záznam si přejete vymazat?");
                System.out.println("- pro vymazání záznamu zadejte index záznamu, například => 1");
                System.out.print("Záznam k vymazání: ");
                int id = Integer.parseInt(sc.nextLine());
                // Přetypování (Parsování) vstupu uživatele do proměnné id, provádí se překlad ze String do Integer
                zaznamy.remove(id); // Vymazání záznamu
                jeSpravne = true; // Nastavení booleanu jeSpravne na true, které ukončí cyklus while

            } catch (Exception e) {
                System.out.println(
                        "Zadaná hodnota neodpovídá indexu žádného ze záznámů.");
            }
        }
    }
}