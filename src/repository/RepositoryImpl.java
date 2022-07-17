package repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RepositoryImpl<I, T> implements Repository<I, T> {

    Map<I, T> repository = new TreeMap<>();

    public boolean exists(Integer key){
        return repository.containsKey(key);
    }

    @Override
    public List<T> buscarTodos() {
        return repository.values().stream().collect(Collectors.toList());
    }

    @Override
    public T buscarPorId(I id) {
        return repository.get(id);
    }

    @Override
    public void salvar(I chave, T objeto) {
        repository.put(chave, objeto);
    }

    @Override
    public void excluir(I id) {
        repository.remove(id);
    }
}