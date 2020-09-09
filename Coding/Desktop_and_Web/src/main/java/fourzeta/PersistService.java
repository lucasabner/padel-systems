package fourzeta;

import java.util.List;

public interface PersistService{
public IElement save(IElement element);
public IElement findbyId(IElement obj, int id);
public List<IElement> findAll(String info);
public IElement remove(IElement obj, int id);

}
