# def binarystrings(k):
#     print("k is " + str(k))
#     if k == 0:
#         return []
#     elif k == 1:
#         return ["1", "0"]
#     else:
#         next_set = binarystrings(k - 1)
#         result = []
#         print("in else")
#         for string in next_set:
#             result.append("1" + string)
#             if string[0] != "0":
#                 result.append("0" + string)
#         print("finished loop")
#         return result

# print(binarystrings(3))

class Circle:
    def __init__(self, c, r):
        self.__center = c
        self.__radius = r

    def __add__(self, other):
        new_center = (self.__center + other.__center) / 2
        new_radius = (self.__radius + other.__radius) / 2
        return Circle(new_center, new_radius)

def symmetric2(word):
    if len(word) < 4:
        raise ValueError
    if word[0:2] == word[len(word):2:-1][::-1]:
        return True

    return False

print(symmetric2("salsa"))
