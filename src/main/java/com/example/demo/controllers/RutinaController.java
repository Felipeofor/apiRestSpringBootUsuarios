package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.EjercicioDto;
import com.example.demo.dto.RutinaDto;
import com.example.demo.models.ApiResponse;
import com.example.demo.models.EjercicioModel;
import com.example.demo.models.RutinaInfo;
import com.example.demo.models.RutinaModel;
import com.example.demo.repositories.EjercicioRepository;
import com.example.demo.repositories.RutinaRepository;
import com.example.demo.services.RutinaService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rutina")
public class RutinaController {

    @Autowired
    private RutinaService rutinaService;
    @Autowired
    private RutinaRepository rutinaRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;

    @GetMapping
    public ResponseEntity<List<RutinaModel>> getAllRutinas() {
        List<RutinaModel> rutina = rutinaRepository.findAll();
        return ResponseEntity.ok(rutina);
    }

    @GetMapping("/nombres-con-ids")
    public ResponseEntity<List<RutinaInfo>> getAllRutinaNamesWithIds() {
        List<RutinaModel> rutinas = rutinaRepository.findAll();
        List<RutinaInfo> rutinaInfoList = new ArrayList<>();

        for (RutinaModel rutina : rutinas) {
            RutinaInfo rutinaInfo = new RutinaInfo();
            rutinaInfo.setId(rutina.getId());
            rutinaInfo.setTitulo(rutina.getTitulo());
            rutinaInfoList.add(rutinaInfo);
        }

        return ResponseEntity.ok(rutinaInfoList);
    }

    @PostMapping("/crear")
    public ResponseEntity<RutinaDto> crearRutina(@RequestBody RutinaDto rutinaDto) {
        // Mapea el RutinaDto a una entidad RutinaModel (si es necesario)
        RutinaModel rutinaModel = mapDtoToRutinaModel(rutinaDto);

        // Guarda la rutina en la base de datos a través del servicio
        rutinaModel = rutinaService.crearRutina(rutinaModel);

        // Mapea la entidad RutinaModel resultante de vuelta a un RutinaDto
        RutinaDto resultadoDto = mapRutinaModelToDto(rutinaModel);

        return new ResponseEntity<>(resultadoDto, HttpStatus.CREATED);
    }

    @GetMapping("/{rutinaId}")
    public ResponseEntity<RutinaDto> obtenerRutinaConEjercicios(@PathVariable Long rutinaId) {
        // Obtiene la rutina de la base de datos a través del servicio
        RutinaModel rutinaModel = rutinaService.obtenerRutinaPorId(rutinaId);

        if (rutinaModel == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        RutinaDto resultadoDto = mapRutinaModelToDtoConEjercicios(rutinaModel);

        return new ResponseEntity<>(resultadoDto, HttpStatus.OK);
    }

    private RutinaModel mapDtoToRutinaModel(RutinaDto rutinaDto) {
        RutinaModel rutinaModel = new RutinaModel();
        rutinaModel.setTitulo(rutinaDto.getTitulo());
        rutinaModel.setImagen(rutinaDto.getImagen());

        return rutinaModel;
    }

    private RutinaDto mapRutinaModelToDto(RutinaModel rutinaModel) {
        RutinaDto rutinaDto = new RutinaDto();
        rutinaDto.setTitulo(rutinaModel.getTitulo());
        rutinaDto.setImagen(rutinaModel.getImagen());

        return rutinaDto;
    }

    private RutinaDto mapRutinaModelToDtoConEjercicios(RutinaModel rutinaModel) {
        RutinaDto rutinaDto = new RutinaDto();
        rutinaDto.setTitulo(rutinaModel.getTitulo());
        rutinaDto.setImagen(rutinaModel.getImagen());

        // Mapea los ejercicios asociados a la rutina
        List<EjercicioDto> ejercicioDtos = new ArrayList<>();
        for (EjercicioModel ejercicioModel : rutinaModel.getEjercicios()) {
            EjercicioDto ejercicioDto = new EjercicioDto();
            ejercicioDto.setId(ejercicioModel.getId());
            ejercicioDto.setDescripcion(ejercicioModel.getDescripcion());
            ejercicioDto.setEjercicioName(ejercicioModel.getEjercicioName());
            ejercicioDto.setRepeticiones(ejercicioModel.getRepeticiones());
            ejercicioDto.setSeries(ejercicioModel.getSeries());
            ejercicioDtos.add(ejercicioDto);
        }
        rutinaDto.setEjercicios(ejercicioDtos);

        return rutinaDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRutina(@PathVariable Long id) {
        Optional<RutinaModel> rutinaOptional = rutinaRepository.findById(id);
        if (rutinaOptional.isPresent()) {

            List<EjercicioModel> ejercicios = ejercicioRepository.findByRutina(rutinaOptional.get());
            ejercicioRepository.deleteAll(ejercicios);

            rutinaRepository.deleteById(id);
            ApiResponse response = new ApiResponse("Rutina eliminada con exito", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Gson().toJson(response));
        } else {
            ApiResponse response = new ApiResponse("No se pudo eliminar la rutina.",
                    HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Gson().toJson(response));
        }
    }
}
