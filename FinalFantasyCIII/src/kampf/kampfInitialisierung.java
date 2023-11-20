package kampf;

import charakter.model.SpielerCharakter;
import party.PartyController;

/**
 * @author Thomas Maass
 * @param BSPTEXT - dokumentiert ein an eine Methode übergebenes Argument.
 * @return BSPTEXT - dokumentiert den Rückgabewert einer Methode.
 * @since BSPTEXT - dient zur Hervorhebung der Einführung in den Code, also seit wann (z.B. Versionsnummer) ein bestimmtes Feature verfügbar ist.
 */
public class kampfInitialisierung {
    //DUMMY VARIABLEN

    // Gegner muessen erstellt werden (von wem auch immer)  und in eine "Array" gelegt werden.

    // Die Daten der Party muessen geholt werden und in einem Array abgelegt werden.
    SpielerCharakter[] team= PartyController.getTeammitglieder();
    // Sortierung der Freunde/Feinde nach "beweglichkeit (int)"
    GegnerTeam gegnerTeam = FeindController.gegnerGenerieren(PartyController);
    // Uebergabe an Melvins Klasse -> kampfDurchfuehrung.


}
