import json

from challenge import Challenge


class Yellow(Challenge):
    name = "yellow"

    def solved(self):
        with open("solves.json", "r") as solves_file:
            return json.load(solves_file)["yellow"]
