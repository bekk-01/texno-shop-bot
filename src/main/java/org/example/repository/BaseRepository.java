package org.example.repository;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.example.model.BaseModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public abstract class BaseRepository<T extends BaseModel> {
    protected String path;
    protected Class<T> type;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);
    public T save(T t){
        ArrayList<T> data = readFromFile();
        data.add(t);
        writeFile(data);
        return t;
    }
    public ArrayList<T> readFromFile(){
        try {
            return objectMapper.readValue(new File(path),
                    TypeFactory.defaultInstance().constructCollectionType(ArrayList.class,type));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeFile(ArrayList<T> data){
        try {
            objectMapper.writeValue(new File(path),data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void delete(UUID id){
        ArrayList<T> data = readFromFile();
        for (T datum : data) {
            if(Objects.equals(datum.getId(),id)){
                data.remove(datum);
                break;
            }
        }
        writeFile(data);
    }
    public ArrayList<T> getAll(){
        return readFromFile();
    }
}
