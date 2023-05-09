package co.personal.StarRailHelper.services;

import co.personal.StarRailHelper.entites.DTO.TraceItemDTO;
import co.personal.StarRailHelper.entites.Trace;
import co.personal.StarRailHelper.entites.TraceItem;
import co.personal.StarRailHelper.exceptions.TraceNotFoundException;
import co.personal.StarRailHelper.exceptions.TraceItemNotFoundException;
import co.personal.StarRailHelper.repositories.TraceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TraceItemService {

    @Autowired
    private TraceService traceService;

    @Autowired
    private TraceItemRepository traceItemRepository;

    private TraceItem getTracesItemsById(Long id) throws TraceItemNotFoundException {
        Optional<TraceItem> optionalTracesItems = traceItemRepository.findById(id);
        if(optionalTracesItems.isPresent()) {
            return optionalTracesItems.get();
        } else {
            throw new TraceItemNotFoundException("Trace Items with id " + id + " not found");
        }
    }

    public TraceItem save(TraceItemDTO traceItemDTO) throws TraceNotFoundException {

        Trace trace = traceService.readByName(traceItemDTO.getTraceName());

        TraceItem traceItem = new TraceItem(
                traceItemDTO.getName(),
                traceItemDTO.getCollected(),
                traceItemDTO.getNeeded(),
                trace
        );
        return traceItemRepository.save(traceItem);

    }

    public TraceItem read(Long id) throws TraceItemNotFoundException {
        return getTracesItemsById(id);
    }

    public TraceItem readByName(String name) throws TraceItemNotFoundException {
        Optional<TraceItem> optionalTracesItems = traceItemRepository.findByName(name);
        if(optionalTracesItems.isPresent()){
            return optionalTracesItems.get();
        } else {
            throw new TraceItemNotFoundException("Trace Item with name " + name + " not found");
        }
    }

    public TraceItem update(Long id, TraceItem traceItem) throws TraceItemNotFoundException {
        TraceItem traceItemUpdate = getTracesItemsById(id);
        traceItemUpdate.setName(traceItem.getName());
        traceItemUpdate.setCollected(traceItem.getCollected());
        traceItemUpdate.setNeeded(traceItem.getNeeded());
        traceItemUpdate.setTrace(traceItem.getTrace());
        return traceItemRepository.save(traceItemUpdate);
    }

    public void delete(Long id) throws TraceItemNotFoundException {
        TraceItem traceItem = getTracesItemsById(id);
        traceItemRepository.delete(traceItem);
    }
}
