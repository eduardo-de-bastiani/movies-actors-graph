import java.util.Scanner;
import java.util.Set;

public class AppMovies
{
	public static String getNome(String s) {
		String nomes[] = s.split(",");
		if (nomes.length == 1)
			return s;
		String result = nomes[1].replaceAll("\\(.*?\\)", "").trim() + " " + nomes[0].replaceAll("\\(.*?\\)", "").trim();
		return result;
	}

	public static void main(String[] args) {
		Graph g = new Graph();

		// Ler o arquivo
		// - para cada linha, extrair:
		//   - o nome do filme
		//   - os nomes das pessoas
		// - criar uma aresta do filme para CADA pessoa
		In arq = new In("movies.txt");
		int cont = 0;
		while(arq.hasNextLine()) {
			String linha = arq.readLine();
			Scanner sc = new Scanner(linha);
			sc.useDelimiter("/");
			// Primeiro nome é o filme
			String nomeFilme = getNome(sc.next());
			while(sc.hasNext()) {
				String nomePessoa = getNome(sc.next());
				if (nomePessoa.contains("Freeman")) {
					System.out.println(nomePessoa);
				}
				g.addEdge(nomeFilme, nomePessoa);
			}
			cont++;
			// if(cont > 2)
				// break;
		}
		System.out.println("Vértices: "+g.totalVertices);
		System.out.println("Arestas: "+g.totalEdges);

		// System.out.println(g.toDot());

		// Processamento:
		// - Ler dois nomes de pessoas
		// - Fazer BFS a partir do primeiro nome
		// - Se houver caminho para o segundo nome:
		//   - mostrar o caminho
		Scanner sc = new Scanner(System.in);
		Set<String> nomes = g.getVerts();
		// for (String nome : nomes) {
			// System.out.println("Nome: "+nome);	
		// }
		System.out.print("De: ");
		String nomeInicial = sc.nextLine();
		if(!nomes.contains(nomeInicial)) {
			System.out.println("Este nome não existe!");
			System.exit(1);
		}
		System.out.print("Para: ");
		String nomeFinal = sc.nextLine();
		if(!nomes.contains(nomeFinal)) {
			System.out.println("Este nome não existe!");
			System.exit(1);
		}

		BFS bfs = new BFS(g, nomeInicial);
		if(bfs.hasPathTo(nomeFinal)) {
			System.out.println("Distância: "+bfs.distTo(nomeFinal));
			for (String nome : bfs.pathTo(nomeFinal)) {
				System.out.println(">>> "+nome);	
			}
		}
		else {
			System.out.println("Não existe ligação!");
		}
	}
}
