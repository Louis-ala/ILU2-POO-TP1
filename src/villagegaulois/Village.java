package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	private int nbEtals;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		this.nbEtals = nbEtals;
		this.marche = new Marche(this.nbEtals);
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	public class Marche {
		private Etal etals[];

		public Marche(int nbetals) {
			this.etals = new Etal[nbetals];
		}
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			this.etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
		}
		
		public int trouverEtalLibre() {
			int compteur = -1;
			for(int i = 0; i < etals.length; i++) {
				if(!(this.etals[i].isEtalOccupe())) {
					return compteur = i;
				}
			}
			return compteur;
		}
		
		public Etal[] trouverEtals(String produit) {
			Etal etalsTrouves[] = new Etal[etals.length + 1];
			for(int i = 0; i < etals.length; i++) {
				if(this.etals[i].contientProduit(produit)) {
					etalsTrouves[i] = this.etals[i];
				}
			}
			return etalsTrouves;
		}
		
		public Etal trouverVendeur(Gaulois gaulois) {
			for(int i = 0; i < etals.length; i++) {
				if(this.etals[i].getVendeur() == gaulois) {
					return this.etals[i];
				}
			}
			return null;
		}
		
		public void afficherMarche() {
			int nbEtalVide = 0;
			for(int i = 0; i < etals.length; i++) {
				if(trouverEtalLibre() == i) {
					nbEtalVide += 1;
				}
				else {
					this.etals[i].afficherEtal();
				}
			}
			System.out.println("Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n ");
		}
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		if(this.marche.trouverEtalLibre() != -1) {
			this.marche.etals[this.marche.trouverEtalLibre()].occuperEtal(vendeur, produit, nbProduit);
			chaine.append(" Le vendeur " + vendeur + " s'est installé à un étal, il vend " + nbProduit + produit + " . ");
		}
		return chaine.toString();
	}
}