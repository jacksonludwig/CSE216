from typing import List


class TwoDPoint:
    def __init__(self, x, y) -> None:
        self.__x = x
        self.__y = y

    @property
    def x(self):
        return self.__x

    @property
    def y(self):
        return self.__y

    def __eq__(self, other) -> bool:
        # was TODO
        if type(self) != type(other):
            return False
        if self.x != other.x or self.y != other.y:
            return False
        return True

    def __ne__(self, other: object) -> bool:
        return not self.__eq__(other)

    def __str__(self) -> str:
        return "(%g, %g)" % (self.__x, self.__y)

    # was TODO: add magic methods such that two TwoDPoint objects can be added and subtracted coordinate-wise just by using
    #  syntax of the form p + q or p - q
    def __add__(self, point):
        if type(self) != type(point):
            raise TypeError("Cannot add a point to any other type")
        x_added = self.x + point.x
        y_added = self.y + point.y
        return TwoDPoint(x_added, y_added)

    def __sub__(self, point):
        if type(self) != type(point):
            raise TypeError("Cannot subtract a point from any other type")
        x_dif = self.x - point.x
        y_dif = self.y - point.y
        return TwoDPoint(x_dif, y_dif)

    @staticmethod
    def from_coordinates(coordinates: List[float]):
        if len(coordinates) % 2 != 0:
            raise Exception("Odd number of floats given to build a list of 2-d points")
        points = []
        it = iter(coordinates)
        for x in it:
            points.append(TwoDPoint(x, next(it)))
        return points
