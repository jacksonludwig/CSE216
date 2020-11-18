from .quadrilateral import Quadrilateral


class ShapeSorter:
    @staticmethod
    def sort(*shapes: Quadrilateral):
        shapes_copy = list(shapes)
        if len(shapes) == 0:
            raise ValueError("Cannot sort an empty list of shapes")

        shapes_copy.sort(key=lambda s: s.smallest_x())
        return shapes_copy
