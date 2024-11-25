from abc import ABC, abstractmethod


class Challenge(ABC):
    name: str

    @abstractmethod
    def solved(self):
        pass
