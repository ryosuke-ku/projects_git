package com.netappsid.observable;


public abstract class AbstractObservableCollectionSupport<E, T> extends DefaultObservableCollectionSupport<E> implements InternalObservableCollectionSupport<E>
{
	private T snapshot;
	private final InternalObservableCollection<E, T> source;

	public AbstractObservableCollectionSupport(InternalObservableCollection<E, T> source)
	{
		super(source);
		this.source = source;
	}

	@Override
	public void fireCollectionChangeEvent()
	{
		fireCollectionChangeEvent(-1);
	}

	@Override
	public void fireCollectionChangeEvent(Object index)
	{
		CollectionChangeEvent<E> collectionChangeEvent = createCollectionChangeEvent(index);
		fireCollectionChangeEvent(collectionChangeEvent);

	}

	protected abstract CollectionChangeEvent<E> createCollectionChangeEvent(Object index);

	@Override
	public void createSnapshot()
	{
		snapshot = takeSnapshot();
	}

	public T takeSnapshot()
	{
		return source.copyInternal();
	}

	/**
	 * @return the snapshot
	 */
	public T getSnapshot()
	{
		return snapshot;
	}
}
