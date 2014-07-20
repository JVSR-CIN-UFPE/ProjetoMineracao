package dataset;

import dataset.models.Artist;
import dataset.models.Rating;
import dataset.models.User;

public class MegaZordController {

	DataBaseFacade dataBase;
	
	public MegaZordController() {
		dataBase = new DataBaseFacade();
	}
	
	// Pearson Similarity ou Cosine Similarity
	
	/**
	 * SOMATORIO[j .. z] = (R(A,j) + Diff(i,j)) + ... + (R(A,z) + Diff(i,z)) <br> <br>
	 * 
	 * P(A,i) = SOMATORIO[j .. z] / N <br> <br>
	 * 
	 * Diff(i,j): é a média das diferenças de avaliações entre itens i e j para os outros usuários <br>
	 * R(A,j): é quanto o usuário A deu de nota ao item j <br>
	 * E supondo que tenhamos N itens e que os itens variem de i a z. <br>
	 * 
	 * @see https://www.ibm.com/developerworks/br/local/data/sistemas_recomendacao/
	 * 
	 * @param userId
	 */
	public void slopeOne(int userId) {
		// FIXME Esta operacao deve estar em cada usuario, pois sera a predicao pra cada um
		
		int quant_artists = dataBase.findAllArtists().size();
		int quant_users = dataBase.findAllUsers().size();
		/* ETAPA DE CALCULO DAS DIFERENCAS */
		
		float[][][] mRatings = new float[quant_users+1][quant_artists+1][quant_artists+1];
		int[][][] mFreq = new int[quant_users+1][quant_artists+1][quant_artists+1];
		
		/*
		No primeiro laço, itera-se por todos os usuários e então por todos os itens, 
		e em mRatings[ i ][ j ] é colocada a soma de notas dadas por todos os usuários 
		que forneceram notas aos itens i e j. Em mFreq[ i ][ j ] é colocada a quantidade 
		de usuários que deram notas a ambos os itens.
		 */
		
		for(User user : dataBase.findAllUsers()) {
			for(Rating rate1 : dataBase.userRatings(user.getId())) {
				for(Rating rate2 : dataBase.userRatings(user.getId())) {
					
					int i = rate1.getArtistID();
					int j = rate2.getArtistID();
					
					mRatings[user.id][i][j] = mRatings[user.id][i][j] + rate1.rate - rate2.rate;
					
					mFreq[user.id][i][j] = mFreq[user.id][i][j] + 1;
				}
			}
		}
		
		/*
		No laço a seguir, a matriz mRatings é percorrida dividindo os valores de mRatings 
		pelos de mFreq, obtendo assim, uma média das diferenças entre avaliações de itens.
		Ao término desse laço, obtemos a matriz de diferenças completa.
		 */
		for(User user : dataBase.findAllUsers()) {
			for (int i = 1; i <= quant_artists; i++) {
				for(int j = i; j <=quant_artists; j++ ){
					if(mFreq[user.id][i][j] > 0) {
						mRatings[user.id][i][j] = mRatings[user.id][i][j] / mFreq[user.id][i][j];
					}
				}
			}
		}
		
		/* ETADA DE PREDICAO */
		float totalFreq[] =  new float [quant_artists+1];
		
		float[] predictions = new float[quant_artists+1];
		
		for (int i = 0; i < predictions.length; i++) {
			predictions[i] = 0.0f;
		}
		
		User user = dataBase.findUser(1); // Usuario alvo (A, da formula) # ITERAR PARA TODOS OS USUARIOS
		
		// Para cada item j que o usuário já avaliou
		for(Rating rate1 : dataBase.userRatings(user.id)) {
			// Para cada item k que o usuário ainda não avaliou
			for (Artist artist : dataBase.findAllArtists()) {
				
				/* Para os itens que o usuario nao avaliou */
				if(rate1.artistID != artist.id) {
					// calculada a nota que o usuário daria para k em relação a j 
					// (newVal = mFreq[ j ][ k ] * (mDiff[ j ][ k ] + user.get(j).floatValue()))
					float newVal = 0;
					
					int j = rate1.getArtistID();
					int k = artist.id;
					
					if(artist.id < rate1.getArtistID()) {
						newVal = mFreq[user.id][j][k] * (mRatings[user.id][j][k] + rate1.rate);
					}
					else {
						newVal = mFreq[user.id][j][k] * (-1 * mRatings[user.id][j][k] + rate1.rate);
					}
					totalFreq[k] = totalFreq[k] + mFreq[user.id][j][k];
					predictions[k] = predictions[k] + newVal;
				}
			}
		}
		
		/* Calcula a média */ 
		for (int j = 0; j < predictions.length; j++) { 
			predictions[j] = predictions[j]/totalFreq[j];
		} 

		/* Preenche o vetor de predições com as avaliações já conhecidas */ 
		for (Rating rate : dataBase.userRatings(user.id)) {
			predictions[rate.artistID] = rate.rate;
		} 

		/* Imprime predições */ 
		System.out.println("\n" + "#### Predictions #### "); 
		for (int j = 0; j < predictions.length; j++) { 
			System.out.println( j + " " + predictions[j]); 
		} 
	}

	public double Inner(double[] x, double[] y) {
		double sum = 0;
		
		for(int i = 0; i < x.length; i++) {
			sum += x[0]*y[0];
		}
		
		return sum;
	}
	
	public double norma(double[] x) {
		return Inner(x, x);
	}
	
	// cosine similarity
	public double CosSim(double[] x, double[] y) {
		return (Inner(x,y)/(norma(x)*norma(y)));
	}
	
	public double medias_aritmeticas(double[] x) {
		double media = 0;
		
		for(double i : x) media += i;
		
		media = media / (x.length);
		
		return media;
	}
	
	// Pearson correlation
	public double Corr(double[] x, double[] y) {
		double x_media = medias_aritmeticas(x);
		double y_media = medias_aritmeticas(y);
		
		for(int i = 0; i < x.length; i++) {
			x[i] = x[i] - x_media;
			y[i] = y[i] - y_media;
		}
		
		return CosSim(x, y);
	}
	
}
