package farmacia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmaciaSunFlower.model.Produto;
import com.farmacia.farmaciaSunFlower.repository.ProdutoRepository;

public class ProdutoController {

	@Autowired
	private ProdutoRepository repositoryP;

	@GetMapping ( " / todos " )

	pública  ResponseEntity<List

	< PRODUTO > >  getAll () {
		List < Produto > listaDeProduto =

	repositoryP . encontrar tudo();

	if(listaDeProduto.isEmpty())

	{
			retornar  ResponseEntity . status ( 204 ) . construir();

		}else
	{
			retornar  ResponseEntity . status ( 200 ) . corpo (listaDeProduto);
		}

	}

	@GetMapping ( " / {idProduto} " )

	public  ResponseEntity < Produto >  GetById ( @PathVariable  long  idProduto ) {
		return repositoryP . findById (idProduto) . map (resp - >  ResponseEntity . ok (resp))
				.orElse ( ResponseEntity . notFound () . build ());
	}

	@GetMapping ( " / descricaoProduto " )

	public  ResponseEntity < Object >  buscarDescricaoProduto ( @RequestParam ( defaultValue  =  " " ) String  descricaoProduto ) {
		List < Produto > listaDeProduto = repositoryP . findAllByDescricaoProdutoContainingIgnoreCase (descricaoProduto);

		if (listaDeProduto . isEmpty ()) {
			retornar  ResponseEntity . status ( 204 ) . construir();

		} else {
			retornar  ResponseEntity . status ( 200 ) . corpo (listaDeProduto);
		}
	}

	@PostMapping
	public  ResponseEntity < Produto >  post ( @RequestBody  Produto  nomeProduto ) {
		retornar  ResponseEntity . status ( 201 ) . corpo (repositórioP . salvar (nomeProduto));
	}

	@PutMapping
	public  ResponseEntity < Produto >  put ( @RequestBody  Produto  nomeProduto ) {
		retornar  ResponseEntity . status ( 200 ) . corpo (repositórioP . salvar (nomeProduto));
	}

	@DeleteMapping ( " / {idProduto} " )
	public  void  delete ( @PathVariable  long  idProduto ) {
		repositoryP . deleteById (idProduto);
	}
}
