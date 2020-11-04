from quadrilateral import Quadrilateral

# TODO after finishing magic methods to compare shapes
class ShapeSorter:
    @staticmethod
    def sort(*shapes):
        if len(shapes) == 0:
            raise ValueError("Cannot sort an empty list of shapes")
        if len(shapes) == 1:
            return shapes[0]

