package co.personal.StarRailHelper.services;

import co.personal.StarRailHelper.entites.DTO.TracesItemsDTO;
import co.personal.StarRailHelper.entites.Trace;
import co.personal.StarRailHelper.entites.TracesItems;
import co.personal.StarRailHelper.exceptions.TraceNotFoundException;
import co.personal.StarRailHelper.exceptions.TracesItemsNotFoundException;
import co.personal.StarRailHelper.repositories.TracesItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TracesItemsService {

    @Autowired
    private TraceService traceService;

    @Autowired
    private TracesItemsRepository tracesItemsRepository;

    private TracesItems getTracesItemsById(Long id) throws TracesItemsNotFoundException{
        Optional<TracesItems> optionalTracesItems = tracesItemsRepository.findById(id);
        if(optionalTracesItems.isPresent()) {
            return optionalTracesItems.get();
        } else {
            throw new TracesItemsNotFoundException("Trace Items with id " + id + " not found");
        }
    }

    public TracesItems save(TracesItemsDTO tracesItemsDTO) throws TraceNotFoundException {

        Trace trace = traceService.readByName(tracesItemsDTO.getTraceName());

        TracesItems tracesItems = new TracesItems(
                tracesItemsDTO.getName(),
                tracesItemsDTO.getCollected(),
                tracesItemsDTO.getNeeded(),
                trace
        );
        return tracesItemsRepository.save(tracesItems);

    }

    public TracesItems read(Long id) throws TracesItemsNotFoundException {
        return getTracesItemsById(id);
    }

    public TracesItems readByName(String name) throws TracesItemsNotFoundException {
        Optional<TracesItems> optionalTracesItems = tracesItemsRepository.findByName(name);
        if(optionalTracesItems.isPresent()){
            return optionalTracesItems.get();
        } else {
            throw new TracesItemsNotFoundException("Trace Item with name " + name + " not found");
        }
    }

    public TracesItems update(Long id, TracesItems tracesItems) throws TracesItemsNotFoundException {
        TracesItems tracesItemsUpdate = getTracesItemsById(id);
        tracesItemsUpdate.setName(tracesItems.getName());
        tracesItemsUpdate.setCollected(tracesItems.getCollected());
        tracesItemsUpdate.setNeeded(tracesItems.getNeeded());
        tracesItemsUpdate.setTrace(tracesItems.getTrace());
        return tracesItemsRepository.save(tracesItemsUpdate);
    }

    public void delete(Long id) throws TracesItemsNotFoundException{
        TracesItems tracesItems = getTracesItemsById(id);
        tracesItemsRepository.delete(tracesItems);
    }
}
