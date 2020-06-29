package Controllers;

import Repository.CharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JosueController {

    @Autowired
    CharactersRepository charactersRepository;

    @GetMapping(value = "/lista",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listarProductos() {
        return new ResponseEntity(charactersRepository.findAll(), HttpStatus.OK);
    }

}
