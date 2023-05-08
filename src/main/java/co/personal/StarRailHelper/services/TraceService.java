package co.personal.StarRailHelper.services;

import co.personal.StarRailHelper.entites.Character;
import co.personal.StarRailHelper.entites.DTO.TraceDTO;
import co.personal.StarRailHelper.entites.Trace;
import co.personal.StarRailHelper.exceptions.CharacterNotFoundException;
import co.personal.StarRailHelper.exceptions.TraceNotFoundException;
import co.personal.StarRailHelper.repositories.TraceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TraceService {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private TraceRepository traceRepository;

    private Trace getTraceById(Long id) throws TraceNotFoundException{
        Optional<Trace> optionalTrace = traceRepository.findById(id);
        if(optionalTrace.isPresent()){
            return optionalTrace.get();
        } else {
            throw new TraceNotFoundException("Trace with id " + id + " not found");
        }
    }

    public Trace save(TraceDTO traceDTO) throws CharacterNotFoundException {

        Character character = characterService.readByName(traceDTO.getCharacterName());

        Trace trace = new Trace(
                traceDTO.getName(),
                character
        );
        return traceRepository.save(trace);
    }

    public Trace read(Long id) throws TraceNotFoundException {
        return getTraceById(id);
    }

    public Trace readByName(String name) throws TraceNotFoundException {
        Optional<Trace> optionalTrace = traceRepository.findByName(name);
        if(optionalTrace.isPresent()){
            return optionalTrace.get();
        } else {
            throw new TraceNotFoundException("Trace with name " + name + " not found");
        }
    }

    public Trace update(Long id, Trace trace) throws TraceNotFoundException {
        Trace traceUpdate = getTraceById(id);
        traceUpdate.setName(trace.getName());
        traceUpdate.setTracesItems(trace.getTracesItems());
        traceUpdate.setCharacter(trace.getCharacter());
        return traceRepository.save(traceUpdate);
    }

    public void delete(Long id) throws TraceNotFoundException{
        Trace trace = getTraceById(id);
        traceRepository.delete(trace);
    }
}
