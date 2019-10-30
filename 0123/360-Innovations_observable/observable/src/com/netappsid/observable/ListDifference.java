package com.netappsid.observable;

import static com.google.common.collect.Lists.*;

import java.util.List;
import java.util.ListIterator;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.netappsid.observable.internal.AbstractCollectionDifference;

public class ListDifference<E> extends AbstractCollectionDifference<E>
{
	private final boolean hasMovedIndexes;

	ListDifference(ImmutableList<E> removed, ImmutableList<E> added, boolean hasMovedIndexes)
	{
		super(removed, added);
		this.hasMovedIndexes = hasMovedIndexes;
	}

	public ListDifference(ImmutableList<E> removed, ImmutableList<E> added)
	{
		this(removed, added, false);
	}

	public boolean isHasMovedIndexes()
	{
		return hasMovedIndexes;
	}

	public static <E> ListDifference<E> difference(ImmutableList<E> oldList, ImmutableList<E> newList)
	{
		final List<E> oldListCopy = newArrayList(oldList);
		final List<E> newListCopy = newArrayList(newList);
		boolean hasMovedIndexes = false;

		for (ListIterator<E> oldListIterator = oldListCopy.listIterator(); oldListIterator.hasNext();)
		{
			final E leftElement = oldListIterator.next();

			for (ListIterator<E> newListIterator = newListCopy.listIterator(); newListIterator.hasNext();)
			{
				if (Objects.equal(leftElement, newListIterator.next()))
				{
					Integer leftIndex = oldListIterator.nextIndex() - 1;
					Integer rightIndex = newListIterator.nextIndex() - 1;

					oldListIterator.remove();
					newListIterator.remove();

					if (!hasMovedIndexes && !leftIndex.equals(rightIndex))
					{
						hasMovedIndexes = true;
					}

					break;
				}
			}

		}

		return new ListDifference<E>(ImmutableList.copyOf(oldListCopy), ImmutableList.copyOf(newListCopy), hasMovedIndexes);
	}

	@Override
	public boolean hasDifference()
	{
		if (super.hasDifference() || hasMovedIndexes)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}