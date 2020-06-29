package Controllers;

import Entity.Characters;
import Repository.CharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin
public class EduardoController {
    @Autowired
    CharactersRepository charactersRepository;

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity guardarCharacter(
            @RequestBody Characters characters ,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {
        HashMap<String, Object> responseMap = new HashMap<>();
        charactersRepository.save(characters);
        if (fetchId) {
            responseMap.put("id", characters.getId());
        }
        responseMap.put("estado", "creado");
        return new ResponseEntity(responseMap, HttpStatus.CREATED);
    }


}
