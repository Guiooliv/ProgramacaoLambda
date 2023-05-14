package exericicio1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter file full path: ");
        String path = scan.nextLine();

        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            List<Product> products = new ArrayList<>();

            String line = br.readLine();

            while(line != null){
                String [] filds = line.split(",");
                String name = filds[0];
                double price = Double.parseDouble(filds[1]);
                products.add(new Product(name, price));
                line = br.readLine();
            }

            double avg = products.stream() // conversão para stream.
                    .map(p -> p.getPrice()) //lista é so de produtos e so quero os preços.
                    .reduce(0.0, (x,y) -> x + y) / products.size(); // reduce permite fazer o somatório dos preços, depois so dividir pelo tamanho da lista.

            System.out.println("Average price:" + String.format("%.2f", avg));

            Comparator<String> comp = Comparator.comparing(String::toUpperCase); // comparando os strings de ordem alfabetica.

            List<String> names = products.stream() //criando nova lista dos nomes dos produtos.
                    .filter(p -> p.getPrice() < avg) //criando outro pipeline, filtrando todo mundo que em o preco menor que a media.
                    .map(p -> p.getName()) //pegar o nome de cada produto.
                    .sorted(comp.reversed()) //ordernar essa stream com base de ordem decrescente de nome. (poderia ter coolodado o comparetor direto no sorted.
                    .collect(Collectors.toList()); // tranformando o string em list novamente.

            names.forEach(System.out::println); //printar a lista names.


        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        scan.close();
    }
}