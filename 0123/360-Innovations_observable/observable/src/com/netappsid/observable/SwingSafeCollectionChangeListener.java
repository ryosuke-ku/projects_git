package com.netappsid.observable;

import java.awt.EventQueue;

import org.apache.log4j.Logger;

public class SwingSafeCollectionChangeListener<E> implements CollectionChangeListener<E>
{
	private static final Logger LOGGER = Logger.getLogger(SwingSafeCollectionChangeListener.class);

	private final CollectionChangeListener<E> listener;

	public SwingSafeCollectionChangeListener(CollectionChangeListener<E> listener)
	{
		this.listener = listener;
	}

	@Override
	public void onCollectionChange(final CollectionChangeEvent<E> event)
	{
		if (EventQueue.isDispatchThread())
		{
			this.listener.onCollectionChange(event);
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
							listener.onCollectionChange(event);
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
