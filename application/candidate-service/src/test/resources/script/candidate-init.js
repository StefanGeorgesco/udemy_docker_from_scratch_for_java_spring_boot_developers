// create db
db = db.getSiblingDB("candidate");

// create user
db.createUser({
    user: "candidate_user",
    pwd: "candidate_password",
    roles: [
        {
            role: "readWrite",
            db: "candidate"
        }
    ]
})

// create collection
db.createCollection("candidate");

// create documents
db.candidate.insertMany([
    {
        _id: '1',
        name: 'John Doe',
        skills: ['JavaScript', 'Node.js', 'MongoDB'],
    },
    {
        _id: '2',
        name: 'Jane Smith',
        skills: ['Python', 'Django', 'PostgreSQL'],
    },
    {
        _id: '3',
        name: 'Alice Johnson',
        skills: ['Java', 'Spring Boot'],
    },
    {
        _id: '4',
        name: 'Bob Brown',
        skills: ['C#', '.NET Core', 'SQL Server'],
    }
]);