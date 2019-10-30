package com.netappsid.observable;

import java.awt.EventQueue;

import org.apache.log4j.Logger;

public class SwingObservableCollectionSupport<E, T extends ObservableCollection<E>> extends DefaultObservableCollectionSupport<E>
{
	private static final Logger LOGGER = Logger.getLogger(SwingObservableCollectionSupport.class);

	public SwingObservableCollectionSupport(T source)
	{
		super(source);
	}

	@Override
	public void fireCollectionChangeEvent(final CollectionChangeEvent<E> event)
	{
		if (EventQueue.isDispatchThread())
		{
			super.fireCollectionChangeEvent(event);
		}
		else
		{
			try
			{
				EventQueue.invokeAndWait(new Runnable()
					{
						@Override
						public void run()
						{
							fireCollectionChangeEvent(event);
						}
					});
			}
			catch (Exception e)
			{
				LOGGER.error(e.getMessage(), e);
			}
		}
	}
}
