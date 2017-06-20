package guicey;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;

public class ResourceModule
    implements Module
{
    @Override
    public void configure(Binder binder)
    {
        binder.bind(DictionaryResource.class).in(Scopes.SINGLETON);
    }
}
