# TODO finish after finishing magic methods to compare shapes
from .quadrilateral import Quadrilateral


class ShapeSorter:
    def __xComparator(s):
        return s.smallest_x()

    @staticmethod
    def sort(*shapes: Quadrilateral):
        shapes_copy = list(shapes)
        if len(shapes) == 0:
            raise ValueError("Cannot sort an empty list of shapes")

        shapes_copy.sort(key=ShapeSorter.__xComparator)
        return shapes_copy
