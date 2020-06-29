package Controllers;

import Entity.Characters;
import Repository.CharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity gestionExcepcion(HttpServletRequest request) {

        HashMap<String, Object> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un Character");
        }
        return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
    }



}
